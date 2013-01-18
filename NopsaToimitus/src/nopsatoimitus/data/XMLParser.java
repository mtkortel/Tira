/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Parseri Google Directions API:n tulostetta varten
 *
 * @author Marko Kortelainen
 */
public class XMLParser {
    XPathFactory factory;
    XPath xpath;
    final String gmap1 = "http://maps.googleapis.com/maps/api/directions/xml?origin=";
    final String gmap2 = "&destination=";
    final String gmap3 = "&sensor=false";
    
    public XMLParser (){
        factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }
    
    public double getDistance(ReittiPiste origin, ReittiPiste dest){
        
        double dd = 0;
        try {
            System.setProperty("http.proxyHost", "cache-services.securitas.fi");
            System.setProperty("http.proxyPort", "8080");
            
            //System.out.println("O: " + origin.getNimi());
            //System.out.println("D: " + dest.getNimi());
            String url = gmap1+origin.getNimi().replaceAll(" ", "+").replaceAll(",", "") +gmap2+
                    dest.getNimi().replaceAll(" ", "+").replaceAll(",", "") +
                    gmap3;
            //System.out.println(url);
            URL gmaps = new URL(url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-services.securitas.fi", 8080));
            HttpURLConnection c = (HttpURLConnection)gmaps.openConnection(proxy);
            c.connect();
            c.setConnectTimeout(5000);
            c.setReadTimeout(5000);
            //System.out.println(c.getInputStream().available());
            InputSource inputXml = new InputSource(c.getInputStream());
            String xpathExpression = "/DirectionsResponse/route/leg/distance/text";
            NodeList nodes = (NodeList) xpath.evaluate(xpathExpression, inputXml, XPathConstants.NODESET);
            int n = nodes.getLength();
            for (int i = 0; i < n; i++){
                //Logger.getLogger(XMLParser.class.getName()).log(Level.INFO, nodes.item(i).getTextContent());
                //System.out.println(nodes.item(i).getNodeName() + " - " + nodes.item(i).getTextContent());
                dd = Double.parseDouble(nodes.item(i).getTextContent().replaceAll(" km", "").trim());
                System.out.println(dd);
            }
        } catch (Exception ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dd;
    }
}
