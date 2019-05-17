package datagenerators;

public interface IFNumGen {
/*Lambda expressions basically express instances of functional interfaces (An interface with single abstract method is called functional interface. An example is java.lang.Runnable).
Lambda expressions implement the only abstract function and therefore implement functional interfaces

lambda expressions are added in Java 8 and provide below functionalities:
- Enable to treat functionality as a method argument, or code as data.
- A function that can be created without belonging to any class.
- A lambda expression can be passed around as if it was an object and executed on demand.

Streams -> internal iteration. Class takes care of the iteration. Compute as soon as requested.
Collection -> external iteration. User takes care of the iteration. Compute after calculating each element*/

    int generate();

    //default void start(Stage primaryStage) throws Exception{ }
}
