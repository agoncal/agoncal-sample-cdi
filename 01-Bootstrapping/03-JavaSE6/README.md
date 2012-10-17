# Sample - CDI - Bootstrapping CDI in Java SE 6

## Purpose of this sample

The purpose of this sample is to use CDI to inject beans (Hello & World) into a Main class (MainJavaSE6) in Java SE 6.

The CDI container is bootstrapped in Java SE using the `org.jboss.weld.environment.se.StartMain` class. This is actually Weld specific as bootstrapping in Java SE is not part of the CDI specification.

[Read more on my blog](http://agoncal.wordpress.com/2011/01/12/bootstrapping-cdi-in-several-environments)

## Class diagram

![image](https://github.com/agoncal/agoncal-sample-cdi-bootstrapping/raw/master/cdibootstrap.png)

## Compile and package

Being Maven centric, you can compile and package this sample with `mvn clean compile`, `mvn clean package` or `mvn clean install`.

## Execute the sample

This sample is executed when `mvn package` is executed (the `org.jboss.weld.environment.se.StartMain` is called during the Maven `package` phase)

You can also test the class with `mvn test`.



<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>