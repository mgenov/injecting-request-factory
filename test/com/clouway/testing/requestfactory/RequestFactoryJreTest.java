package com.clouway.testing.requestfactory;

import com.clouway.requestfactory.app.inject.InjectingServiceLayerDecorator;
import com.clouway.requestfactory.app.inject.RequestFactoryInjectingModule;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;
import org.junit.Before;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.resetToDefault;

/**
 * Provides an way of directly injection of {@link com.google.gwt.requestfactory.shared.RequestFactory} classes and target classes marked as {@literal@}ProvidedService. To provide injection
 * in your class you have to extends the {@link RequestFactoryJreTest}, after that you are free to inject service classes and RequestFactory classes.
 * <p/>
 * Here is an example how to accomplish this:
 * <pre>
 * {@literal@}Factories(CustomerRequestFactory.class)
 * public class CustomerEditorTest extends RequestFactoryJreTest {
 *   {@literal@}Inject
 *   CustomerRequestFactory crf;
 *
 *   {@literal@}Inject
 *   CustomerService customerService;
 *
 *   {@literal@}Test
 *   public void customerIsLoaded() {
 *      CustomerEditor editor = new CustomerService(crf);
 *
 *      expect(customerService.loadCustomer(1l)).andReturn(new Customer(1l, "Customer1");
 *      replay(customerService);
 *
 *      editor.onLoadCustomer(1l);
 *
 *      verify(customerService);
 *   }
 * }
 *
 * </pre>
 * <p/>
 * To may run RequestFactory classes in simple JRE test, the {@link RequestFactoryJreTest} is using {@link com.google.gwt.requestfactory.server.testing.RequestFactoryMagic#create(Class)}  method
 * to create the application {@link com.google.gwt.requestfactory.shared.RequestFactory} then is initializing it using {@link com.google.gwt.requestfactory.server.testing.InProcessRequestTransport} and the EventBus.
 * <p/>
 * RequestFactoryJreTest is an base test of all tests that are using {@link com.google.gwt.requestfactory.shared.RequestFactory} and are using mock objects
 * to ensure that service methods are called correctly and are called in correct order.
 * <p/>
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public abstract class RequestFactoryJreTest {
  /**
   * We need to keep reference to all service instances (mocked instances), so we could "reset" them before each usage. This
   * is required by the fact that RequestFactory's ServiceLayer is caching every service instance and re-uses it all the time.
   */
  private static Map<Class<?>, Object> serviceInstances = new ConcurrentHashMap<Class<?>, Object>();

  /**
   * Creates an Injector that is injecting the RequestFactory and target service classes into the target test class.
   */
  @Before
  public void initialize() {
    final SimpleEventBus eventBus = new SimpleEventBus();


    Factories factoryAnnotation = getClass().getAnnotation(Factories.class);
    if (factoryAnnotation == null) {

      throw new IllegalStateException("@Factories annotation is missing on your test. Please check you test class and be sure" +
              "that @Factories annotation is specified.");
    }

    final Set<Class<? extends RequestFactory>> factories = new HashSet<Class<? extends RequestFactory>>(Arrays.asList(factoryAnnotation.value()));

    final List<RequestFactory> boundFactories = new ArrayList<RequestFactory>(factories.size());

    Injector injector = Guice.createInjector(
            new RequestFactoryInjectingModule("/gwtRequest"),
            new AbstractModule() {

              @Override
              protected void configure() {
                bind(EventBus.class).toInstance(eventBus);

                for (Class<? extends RequestFactory> factory : factories) {

                  Set<Class<?>> serviceClazz = resolveServiceClasses(factory);

                  RequestFactory rf = bindRequestFactory(binder(), factory);
                  boundFactories.add(rf);

                  for (Class<?> clazz : serviceClazz) {
                    bindServiceClass(binder(), clazz);
                  }

                }
              }
            }
    );

    InjectingServiceLayerDecorator decorator = injector.getInstance(InjectingServiceLayerDecorator.class);

    InProcessRequestTransport transport = new InProcessRequestTransport(new SimpleRequestProcessor(ServiceLayer.create(decorator)));


    injector.injectMembers(this);

    for (RequestFactory rf : boundFactories) {
      rf.initialize(eventBus, transport);
    }
  }

  /**
   * Resolves service classes from the defined RequestFactory class by using method return types
   * and {@literal@}Service annotation that is bound to the return type.
   */
  private Set<Class<?>> resolveServiceClasses(Class<? extends RequestFactory> factory) {

    // we are skipping standard methods inherited from java.lang.Object.
    Method[] methods = factory.getDeclaredMethods();

    // set structure is used to ensure that single service class would be injected
    // only once.
    Set<Class<?>> targetServiceClasses = new LinkedHashSet<Class<?>>();

    for (Method method : methods) {
      Class<?> requestMethod = method.getReturnType();
      Service service = requestMethod.getAnnotation(Service.class);
      if (service != null && service.value() != null) {
        targetServiceClasses.add(service.value());
      }
    }

    return targetServiceClasses;
  }

  /**
   * Binds target service class to a mock object.
   */
  private <T> T bindServiceClass(Binder binder, final Class<T> serviceClazz) {

    T serviceInstance = null;

    if (!serviceInstances.containsKey(serviceClazz)) {
      serviceInstance = createMock(serviceClazz);

      serviceInstances.put(serviceClazz, serviceInstance);

    }
    serviceInstance = (T) serviceInstances.get(serviceClazz);
    resetToDefault(serviceInstance);

    //TODO{mgenov}: Think how to bind to some real objects?
    binder.bind(serviceClazz).toInstance(serviceInstance);

    return serviceInstance;
  }


  /**
   * Creates new instance of {@link RequestFactory} and binds it to the provided Guice Binder, so it
   * could be injected when test is requesting such injection.
   */
  private <T extends RequestFactory> T bindRequestFactory(Binder binder, Class<T> clazz) {
    T t = RequestFactorySource.create(clazz);
    binder.bind(clazz).toInstance(t);
    return t;
  }


}