package mySession;

import junit.framework.TestCase;
import mySession.event.SessionBindingEvent;
import mySession.listener.SessionAttributeListener;

/**
 * Created by Worker on 06.03.2016.
 */
public class CustomHttpSessionOnClientRepositoryTest extends TestCase {


    public void testSessionToString() throws Exception {
        ShopSession session = new HeavySession("", new EmptySessionAttributeListener());
        session.setAttribute("a", "123");
        CustomHttpSessionOnClientRepository repository = new CustomHttpSessionOnClientRepository();
        String result = repository.sessionToString(session);
        assertEquals(a123Session, result);
    }

    public void testStringToSession() throws Exception {
        CustomHttpSessionOnClientRepository repository = new CustomHttpSessionOnClientRepository();
        ShopSession session = repository.stringToSession(a123Session);
        Object result = session.getAttribute("a");
        assertEquals("123", result);
    }

    class EmptySessionAttributeListener implements SessionAttributeListener {
        @Override
        public void attributeAdded(SessionBindingEvent event) {

        }

        @Override
        public void attributeRemoved(SessionBindingEvent event) {

        }

        @Override
        public void attributeReplaced(SessionBindingEvent event) {

        }
    }

    String a123Session = "rO0ABXNyABZteVNlc3Npb24uSGVhdnlTZXNzaW9unaQxG18zl" +
            "QMCAAFMAARpbXBsdAAPTGphdmEvdXRpbC9NYXA7eHBzcgAmam" +
            "F2YS51dGlsLmNvbmN1cnJlbnQuQ29uY3VycmVudEhhc2hNYXBkmd4Sn" +
            "YcpPQMAA0kAC3NlZ21lbnRNYXNrSQAMc2VnbWVudFNoaWZ0WwAIc2VnbWVud" +
            "HN0ADFbTGphdmEvdXRpbC9jb25jdXJyZW50L0NvbmN1cnJlbnRIYXNoTWFwJF" +
            "NlZ21lbnQ7eHAAAAAPAAAAHHVyADFbTGphdmEudXRpbC5jb25jdXJyZW50LkNv" +
            "bmN1cnJlbnRIYXNoTWFwJFNlZ21lbnQ7Unc/QTKbOXQCAAB4cAAAABBzcgAuamF" +
            "2YS51dGlsLmNvbmN1cnJlbnQuQ29uY3VycmVudEhhc2hNYXAkU2VnbWVudB82T" +
            "JBYkyk9AgABRgAKbG9hZEZhY3RvcnhyAChqYXZhLnV0aWwuY29uY3VycmVudC5s" +
            "b2Nrcy5SZWVudHJhbnRMb2NrZlWoLCzIausCAAFMAARzeW5jdAAvTGphdmEvdXRp" +
            "bC9jb25jdXJyZW50L2xvY2tzL1JlZW50cmFudExvY2skU3luYzt4cHNyADRqYXZh" +
            "LnV0aWwuY29uY3VycmVudC5sb2Nrcy5SZWVudHJhbnRMb2NrJE5vbmZhaXJTeW5j" +
            "ZYgy51N7vwsCAAB4cgAtamF2YS51dGlsLmNvbmN1cnJlbnQubG9ja3MuUmVlbnRyY" +
            "W50TG9jayRTeW5juB6ilKpEWnwCAAB4cgA1amF2YS51dGlsLmNvbmN1cnJlbnQub" +
            "G9ja3MuQWJzdHJhY3RRdWV1ZWRTeW5jaHJvbml6ZXJmVahDdT9S4wIAAUkABXN0" +
            "YXRleHIANmphdmEudXRpbC5jb25jdXJyZW50LmxvY2tzLkFic3RyYWN0T3duYWJs" +
            "ZVN5bmNocm9uaXplcjPfr7mtbW+pAgAAeHAAAAAAP0AAAHNxAH4ACHNxAH4ADAA" +
            "AAAA/QAAAc3EAfgAIc3EAfgAMAAAAAD9AAABzcQB+AAhzcQB+AAwAAAAAP0AAAH" +
            "NxAH4ACHNxAH4ADAAAAAA/QAAAc3EAfgAIc3EAfgAMAAAAAD9AAABzcQB+AAhzc" +
            "QB+AAwAAAAAP0AAAHNxAH4ACHNxAH4ADAAAAAA/QAAAc3EAfgAIc3EAfgAMAAAAA" +
            "D9AAABzcQB+AAhzcQB+AAwAAAAAP0AAAHNxAH4ACHNxAH4ADAAAAAA/QAAAc3EAf" +
            "gAIc3EAfgAMAAAAAD9AAABzcQB+AAhzcQB+AAwAAAAAP0AAAHNxAH4ACHNxAH4AD" +
            "AAAAAA/QAAAc3EAfgAIc3EAfgAMAAAAAD9AAABzcQB+AAhzcQB+AAwAAAAAP0AAA" +
            "HQAAWF0AAMxMjNwcHg=";

}