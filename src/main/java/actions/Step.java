package actions;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * A step in an acceptance test.
 * Steps are like mini unit-tests, executed in a predetermined order.
 *
 */
@Retention(RetentionPolicy.RUNTIME) // θα ειναι διαθεσιμη οταν τρεχει το προγραμμα
@Target(ElementType.METHOD) // μονο σε μεθοδους
public @interface Step {  // ορισμος νεου annotation
    String value() default "";  //να μπορει να χρησιμοποιειται σκετο
    boolean fluent() default false; // για fluent steps
    boolean callNestedMethods() default true; // μπορω να καλω διαφορετικες @steps
    boolean exampleRow() default false;
}
