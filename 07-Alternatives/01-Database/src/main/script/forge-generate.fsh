# ##########################################################
# Forge Script Generating the web application for the sample
# ##########################################################


# Creates the project
# ###################

project-new --named alternatives-database --topLevelPackage org.agoncal.sample.cdi.alternatives.database --finalName sampleCDIAlternativesDatabase --type war ;

# Adds a few extra files and directories
touch src/main/resources/insert.sql ;
mkdir src/main/script ;
touch src/main/script/jboss-setup.cli ;
touch src/main/script/jboss-clean.cli ;
touch src/main/script/jboss-show.cli ;
touch src/main/script/forge-generate.fsh ;

# Sets the project to use Java EE 7 artifacts instead of the defaults Java EE 6

jpa-setup --jpaVersion 2.1 --persistenceUnitName alternative-test-pu --dbType H2       --dataSourceName java:/global/datasources/H2DS ;
jpa-setup --jpaVersion 2.1 --persistenceUnitName alternative-dev-pu  --dbType DERBY    --dataSourceName java:/global/datasources/DerbyDS ;
jpa-setup --jpaVersion 2.1 --persistenceUnitName alternative-prod-pu --dbType POSTGRES --dataSourceName java:/global/datasources/PostgresDS ;

cdi-setup --cdiVersion 1.1 ;
faces-setup --facesVersion 2.2 ;
scaffold-setup --servletVersion 3.1 ;
# missing constraint-setup --beanValidationVersion 1.1 ;


# Creates an entity
# #################

jpa-new-entity --named Speaker ;
jpa-new-field --named firstname ;
jpa-new-field --named surname ;
jpa-new-field --named bio --length 2000 ;
jpa-new-field --named twitter ;

constraint-add --onProperty firstname --constraint NotNull ;
constraint-add --onProperty surname --constraint NotNull ;
constraint-add --onProperty bio --constraint Size --max 2000 ;


# Creates CDI beans
# #################

cdi-new-bean --named DatabaseProducer --targetPackage org.agoncal.sample.cdi.alternatives.database.cdi ;

cdi-new-stereotype --named DevDatabase --targetPackage org.agoncal.sample.cdi.alternatives.database.cdi --alternative ;
cdi-new-stereotype --named ProdDatabase --targetPackage org.agoncal.sample.cdi.alternatives.database.cdi --alternative ;


# Scaffolds a JSF application
# ###########################

scaffold-generate --targets org.agoncal.sample.cdi.alternatives.database.model.Speaker ;


# Cleaning up the pom.xml
# #######################

project-remove-dependencies org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar:: ;
project-remove-dependencies javax.enterprise:cdi-api:jar:: ;
project-remove-dependencies javax.faces:javax.faces-api:jar:: ;
project-remove-dependencies org.jboss.spec.javax.ejb:jboss-ejb-api_3.1_spec:jar:: ;
project-remove-dependencies javax.servlet:javax.servlet-api:jar:: ;
project-remove-dependencies javax.validation:validation-api:jar:: ;

project-remove-managed-dependencies org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar::1.0.0.Draft-16 ;
project-remove-managed-dependencies javax.enterprise:cdi-api:jar::1.1 ;
project-remove-managed-dependencies javax.faces:javax.faces-api:jar::2.2 ;
project-remove-managed-dependencies javax.servlet:javax.servlet-api:jar::3.1.0 ;
project-remove-managed-dependencies org.jboss.spec:jboss-javaee-6.0:pom::3.0.2.Final ;

project-add-managed-dependencies javax:javaee-api:7.0:provided ;
project-add-managed-dependencies org.apache.derby:derbyclient:10.10.2.0 ;
project-add-managed-dependencies org.postgresql:postgresql:9.3-1101-jdbc4 ;

project-add-dependencies javax:javaee-api ;
project-add-dependencies org.apache.derby:derbyclient ;
project-add-dependencies org.postgresql:postgresql ;


