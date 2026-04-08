package io.openliberty.mcp.internal.fat.tool;

import static com.ibm.websphere.simplicity.ShrinkHelper.DeployOptions.SERVER_ONLY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;

import com.ibm.websphere.simplicity.ShrinkHelper;

import componenttest.annotation.Server;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.FATServletClient;
import io.openliberty.mcp.internal.fat.observability.mpmetrics.McpMetricBeanMpMetric;
import io.openliberty.mcp.internal.fat.utils.McpClient;

@RunWith(FATRunner.class)
public class MpMetricsTest extends FATServletClient {

    private final static String APP_NAME = "mpMetricTest";

    @Server("mcp-server-mpmetrics")
    public static LibertyServer server;

    @Rule
    public McpClient client = new McpClient(server, "/" + APP_NAME);

    private static final String BASIC_TOOL_REQUEST = """
                      {
                      "jsonrpc": "2.0",
                      "id": 2,
                      "method": "tools/call",
                      "params": {
                        "name": "basicTool",
                        "arguments": {}
                      }
                    }
                    """;

    private static final String ADVANCED_TOOL_REQUEST = """
                      {
                      "jsonrpc": "2.0",
                      "id": 2,
                      "method": "tools/call",
                      "params": {
                        "name": "advancedTool",
                        "arguments": {}
                      }
                    }
                    """;

    @BeforeClass
    public static void setup() throws Exception {
        WebArchive war = ShrinkWrap.create(WebArchive.class, APP_NAME + ".war")
                                   .addPackage(McpMetricBeanMpMetric.class.getPackage());

        ShrinkHelper.exportDropinAppToServer(server, war, SERVER_ONLY);

        server.startServer();
    }

    @AfterClass
    public static void teardown() throws Exception {
        server.stopServer("CWWKS9113E");
    }

    @Test
    public void testToolCallMetrics() throws Exception {
        String response = client.callMCP(BASIC_TOOL_REQUEST);
        String expectedResponseString = """
                        {"id":2,"jsonrpc":"2.0","result":{"content":[{"type":"text","text": "Hello from this basic tool"}], "isError": false}}
                        """;
        JSONAssert.assertEquals(expectedResponseString, response, true);

        String metric = requestHttpServlet(server);
        System.out.println("**HABIBLAWAL** Metric URL Output -> " + metric);

        client.callMCP(ADVANCED_TOOL_REQUEST);
        client.callMCP(ADVANCED_TOOL_REQUEST);

        // Run servlet tests to see if metrics are collected correctly
//        FATServletClient.runTest(server, APP_NAME + "/McpMetricServlet", "testBasicToolCallMetrics");
//        FATServletClient.runTest(server, APP_NAME + "/McpMetricServlet", "testAdvancedToolCallMetrics");
    }

    protected String requestHttpServlet(LibertyServer server) {
        HttpURLConnection con = null;
        try {
            String url = "http://9.71.246.72:8010/metrics/";

            URL checkerServletURL = new URL(url);
            con = (HttpURLConnection) checkerServletURL.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod("GET");
            String sep = System.getProperty("line.separator");
            String line = null;
            StringBuilder lines = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((line = br.readLine()) != null && line.length() > 0) {
                lines.append(line).append(sep);
            }
            return lines.toString();
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (con != null)
                con.disconnect();
        }

    }

}
