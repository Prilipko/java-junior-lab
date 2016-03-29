package groovyTest

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext

def ctx = new GenericApplicationContext();
def reader = new GroovyBeanDefinitionReader(ctx);

reader.beans {
    contact(Contact, firstName: 'Sasha', lastName: "P", age: 30)
}

ctx.refresh();

println ctx.getBean("contact")
