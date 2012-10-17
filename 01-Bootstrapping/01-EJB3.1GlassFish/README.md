# Sample - CDI - Bootstrapping CDI in EJBs 3.1 with GlassFish 3.x

## Purpose of this sample

The purpose of this sample is to use CDI to inject beans (Hello & World) into an EJB (MainEJB31) in GlassFish 3.x. We then use an integration test class (MainEJBTest) to make sure the injection has been made.

The CDI container is bootstrapped automatically in GlassFish.

[Read more on my blog](http://antoniogoncalves.org/2011/01/12/bootstrapping-cdi-in-several-environments/)

## Class diagram

![image](https://github.com/agoncal/agoncal-sample-cdi-bootstrapping/raw/master/cdibootstrap.png)

## Compile and test

Being Maven centric, you can compile and test this sample with `mvn clean test`.

You could also package your EJB with `mvn package` and deploy it to GlassFish.

You can also use the Maven GlassFish plugin, run `mvn clean package embedded-glassfish:run` and go to [http://localhost:8080/sampleCdiBootstrappingEjb31Glassfish]()



<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>