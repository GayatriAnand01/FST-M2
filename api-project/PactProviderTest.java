package Project;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("userProvider")
@PactFolder("target/pacts")
//@PactBroker(host = "companyname.pactflow.io")
public class PactProviderTest {

    //settinf the target
    @BeforeEach
    public void setUp(PactVerificationContext context){
        HttpTestTarget target = new HttpTestTarget("localhost",8585);
        context.setTarget(target);

    }

   //Test template used for common
   @TestTemplate
   @ExtendWith(PactVerificationInvocationContextProvider.class)
   public void providerTest(PactVerificationContext context){
       context.verifyInteraction();
   }

    @State("A request to create a user")
    public void sampleState()
    {

    }


}
