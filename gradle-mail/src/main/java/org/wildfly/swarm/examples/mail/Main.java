package org.wildfly.swarm.examples.mail;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.mail.MailFraction;

/**
 * @author Helio Frota
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final Container container = new Container();
        final MailFraction fraction = new MailFraction();
        final String host = System.getProperty("smtp.host");
        final String port = System.getProperty("smtp.port");

        System.out.println(String.format("Using smtp server at %s:%s", host, port));

        container.fraction(fraction.smtpServer("ExampleName", s -> s.host(host).port(port)))
                .start()
                .deploy(ShrinkWrap.create(JAXRSArchive.class)
                                .addClass(Mail.class)
                                .addAllDependencies());
    }
}
