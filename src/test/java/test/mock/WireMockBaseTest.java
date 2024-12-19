package test.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class WireMockBaseTest {

    protected WireMockServer wireMockServer;

    @BeforeSuite
    public void startWireMockServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8090)); // Port 8080
        wireMockServer.start();
        System.out.println("WireMock server started on port: " + wireMockServer.port());
    }

    @AfterSuite
    public void stopWireMockServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
            System.out.println("WireMock server stopped.");
        }
    }
}
