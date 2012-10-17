# Sample - CDI - Bootstrapping CDI in Servlet 3.0 with GlassFish 3.x

## Purpose of this sample

The purpose of this sample is to use CDI to inject beans (Hello & World) into a Servlet (MainServlet30) in GlassFish 3.x.

The CDI container is bootstrapped automatically in GlassFish.

[Read more on my blog](http://agoncal.wordpress.com/2011/01/12/bootstrapping-cdi-in-several-environments)

## Class diagram

![image](https://github.com/agoncal/agoncal-sample-cdi-bootstrapping/raw/master/cdibootstrap.png)

## Compile and package

Being Maven centric, you can compile and package this sample with `mvn clean compile`, `mvn clean package` or `mvn clean install`.

## Deploy the sample

Copy the `sampleCdiBootstrappingServlet30Glassfish.war` file to `$GLASSFISH_HOME/glassfish/domains/domain1/autodeploy` or use the GlassFish admin command line : `asadmin deploy --force sampleCdiBootstrappingServlet30Glassfish.war`.

## Execute the sample

Once deployed, go to the following URL to invoke the servlet : `http://localhost:8080/sampleCdiBootstrappingServlet30Glassfish/mainServlet30`



<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>