package org.spincast.todobackend.inmemory;

import org.spincast.core.config.ISpincastConfig;
import org.spincast.defaults.guice.SpincastDefaultGuiceModule;
import org.spincast.todobackend.inmemory.config.AppConfig;
import org.spincast.todobackend.inmemory.config.IAppConfig;
import org.spincast.todobackend.inmemory.controllers.ITodoController;
import org.spincast.todobackend.inmemory.controllers.TodoController;
import org.spincast.todobackend.inmemory.repositories.ITodoRepository;
import org.spincast.todobackend.inmemory.repositories.InMemoryTodoRepository;
import org.spincast.todobackend.inmemory.services.ITodoService;
import org.spincast.todobackend.inmemory.services.TodoService;

import com.google.inject.Scopes;

/**
 * The application's custom Guice module.
 * 
 * It extends "SpincastDefaultGuiceModule" so we start with
 * the default bindings.
 */
public class AppModule extends SpincastDefaultGuiceModule {

    @Override
    protected void configure() {
        super.configure();

        //==========================================
        // One instance only of our configuration class.
        //==========================================
        bind(AppConfig.class).in(Scopes.SINGLETON);

        //==========================================
        // Binds our configuration implementation class to our
        // "IAppConfig" interface.
        //==========================================
        bind(IAppConfig.class).to(AppConfig.class).in(Scopes.SINGLETON);

        //==========================================
        // Binds our controller / service / repository.
        //==========================================
        bind(ITodoController.class).to(TodoController.class).in(Scopes.SINGLETON);
        bind(ITodoService.class).to(TodoService.class).in(Scopes.SINGLETON);
        bind(ITodoRepository.class).to(InMemoryTodoRepository.class).in(Scopes.SINGLETON);

        //==========================================
        // Binds the App itself.
        //==========================================
        bind(App.class).in(Scopes.SINGLETON);
    }

    /**
     * We tell Spincast to use our custom configuration class
     * as the implementation for its "ISpincastConfig" component.
     */
    @Override
    protected void bindConfigPlugin() {
        bind(ISpincastConfig.class).to(AppConfig.class).in(Scopes.SINGLETON);
    }

}