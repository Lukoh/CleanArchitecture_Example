Android Architecture 1.0 Blueprint
  The Android framework provides a lot of flexibility in deciding how to organize and architect all Android Apps. While this freedom is very valuable, it can also lead to apps with large classes, inconsistent naming schemes, as well as mismatching or missing architectures. These types of issues can make testing, maintaining and extending your apps difficult.
The Android Architecture Blueprints project demonstrates strategies to help solve or avoid these common problems. This project implements the same app using different architectural concepts and tools.
You can use all source code in this project as a learning reference, or as a starting point for creating Android apps. The focus of this project is on demonstrating how to structure your code, design your architecture, and the eventual impact of adopting these patterns on testing and maintaining Android app. You can use the techniques demonstrated here in many different ways to build apps. Your own particular priorities will impact how you implement the concepts in these projects, so you should not consider these samples to be canonical examples. To ensure the focus in kept on the aims described above, the app uses a simple UI.
 Summary
Base Android Architecture consist of  Presentation layer ,  Domain layer  and  Repository layer . And new latest technologies,  Clean Architecture  +  Dagger 2.0  +  MVP design pattern , were applied into  Base Android Architecture . These stuff make Android Apps to be extended being more competitive power and help them to maintain consistency.
Base Android Architecture
  
 Data Communication Diagram
 EventBus
EventBus is a publish/subscribe event bus optimized for Android.
 
 This Android Architecture Blueprints project stands on the principles of  Clean Architecture  +  MVP  + Dagger 2.0 . All Android apps have to be built being based on the Android Architecture Blueprints project.
● Clean Architecture  : Clean Architecture is essential reading for every software architect, systems analyst, system designer, and software manager - and for any programmer who aspires to these roles or is impacted by their work.
○ Domain Layer  : Holds all business logic. The domain layer starts with classes named use cases or interactor used by the application presenters. These use cases represent all the possible actions a developer can perform from the presentation layer.
■ Interactor : All the external components use Interactor's interfaces when connecting to the business objects. Interactor provides interfaces to access to all business logic. That means Interactor is coupled with Usecase. All interfaces in Interactor used by the application presenters.
○ Repository Layer  : All data needed for the application comes from this layer through a Communicator implementation (the interface is in the domain layer) that uses a Repository Pattern with a strategy that, through a factory, picks different data sources depending on certain conditions.
■ Data Helper : Data Helper provides developers with a simple way to manipulate internal data such as Preference data or configuration data for setting with an existing data handling modules and to manage its initial creation and any upgrades.
○ Presentation Layer  : Fragments and activities are only views, there is no logic inside them other than UI logic, and this is where all the rendering stuff takes place.
● MVP  : Model View Presenter design pattern
● Dagger 2.0  : Dagger is a fully static, compile-time dependency injection framework and is a
compile-time evolution approach to dependency injection. Dagger enable  our project’s
performance to improve over 15 %  if we could apply it to our project.
● Auto Value  : Google’s AutoValue library makes them much easier and has just received the
long awaited update that adds the flexibility of extensions.
● Event Bus  : EventBus is an open-source library for Android using the publisher/subscriber
pattern for loose coupling. EventBus enables central communication to decoupled classes – simplifying the code, removing dependencies, and speeding up app development.
EventBus
○ simplifies the communication between components
■ decouples event senders and receivers
■ performs well with Activities, Fragments, and background threads
■ avoids complex and error-prone dependencies and life cycle issues
○ makes your code simpler
○ is fast
○ is tiny (~50k jar)
○ is proven in practice by apps with 100,000,000+ installs
○ has advanced features like delivery threads, subscriber priorities, etc.
Technologies to be applied in the future
● RxJava  : RxJava is a Java VM implementation of ReactiveX (Reactive Extensions) : A library for composing asynchronous and event-based programs by using observable sequences.
● RxAndroid  : Reactive Extensions for Android : This module adds the minimum classes to RxJava that make writing reactive components in Android applications easy and hassle-free.
Requirements
● JDK 1.8
● Android SDK
● Android 5.0  -  Android Architecture  supports over Android 5.0
● Latest Android SDK Tools and build tools.
Dependencies
All libraries below enable Android App to be efficient and improve the performance. That means you have to use those libraries to make Android App.
Leverages third-party libraries:
● Retrofit  - A type-safe HTTP client for Android and Java(For asynchronous network requests)
● EventBus  - For communication between Activities, Fragments, Service, etc
● ButterKnife  - For field and method binding for Android views
● Glide  - For an image loading and caching library for Android focused on smooth scrolling
● SwipyRefreshLayout  - For swiping in both direction
● MaterialDrawer  - For using drawer menu
● SlidingDrawer  - I added/implemented more goood containers to be input into MaterialDrawer.
Please refer to  SlidingDrawer
