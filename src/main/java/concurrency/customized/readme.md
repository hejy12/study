引言
==

Java 并发API提供许多接口和类来实现并发应用程序。它们提供底层（low-level）机制，如Thread类、Runnable或Callable接口、或synchronized关键字。同样也提供高级（high-level）机制，如Executor框架和Java 7 发布的Fork/Join框架。尽管这样，你可能发现你自己开发一个程序时，没有一个java类能满足你的需求。

在这种情况下，你也许需要基于Java提供的（API）实现自己定制的并发工具。基本上，你可以：

    实现一个接口提供那个接口定义的功能。比如：ThreadFactory接口。
    覆盖一个类的一些方法来调整它的行为以满足你的需求。比如，覆盖Thread类的run()方法，默认情况下，它没有用并且应该被覆盖以提供一些功能。

通过这个文章的指南，你将学习如何改变一些Java并发API类的行为，而不必从头开始设计一个并发框架。你可以使用这些指南作为初始点来实现你自己的定制。 