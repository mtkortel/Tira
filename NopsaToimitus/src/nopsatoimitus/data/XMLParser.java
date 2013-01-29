/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nopsatoimitus.data;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
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
    
    /**
     * Konstruktori
     */
    public XMLParser (){
        factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }
    
    /**
     * Etäisyyden haku
     * @param origin    Lähtöpiste
     * @param dest      Kohdepiste
     * @return          Etäisyys kilometreissä
     */
    public double getDistance(ReittiPiste origin, ReittiPiste dest){
        double dd = 0;
        try {
            System.setProperty("http.proxyHost", "cache-services.securitas.fi");
            System.setProperty("http.proxyPort", "8080");
            NodeList nodes = getDist(origin, dest);
            int n = nodes.getLength();
            for (int i = 0; i < n; i++){
                dd = Double.parseDouble(nodes.item(i).getTextContent().replaceAll(" km", "").trim());
            }
        } catch (Exception ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dd;
    }

    /**
     * Metodi, joka hakee Google Maps API:a hyväksikäyttäen etäisyystiedot
     * @param origin    Lähtöpiste
     * @param dest      Kohdepiste
     * @return          NodeList, jossa XML-muodossa reittitiedot
     * @throws IOException
     * @throws XPathExpressionException
     * @throws MalformedURLException 
     */
    private NodeList getDist(ReittiPiste origin, ReittiPiste dest) throws IOException, XPathExpressionException, MalformedURLException {
        String url = gmap1+origin.getNimi().replaceAll(" ", "+").replaceAll(",", "") +gmap2+
                dest.getNimi().replaceAll(" ", "+").replaceAll(",", "") +
                gmap3;
        InputSource inputXml = getXML(url);
        String xpathExpression = "/DirectionsResponse/route/leg/distance/text";
        NodeList nodes = (NodeList) xpath.evaluate(xpathExpression, inputXml, XPathConstants.NODESET);
        return nodes;
    }
    /**
     * Metodi hakee XML viestin Google Maps API:lta
     * @param url   Osoite josta viesti haetaan
     * @return      XML-syöte
     * @throws MalformedURLException
     * @throws IOException 
     */
    private InputSource getXML(String url) throws MalformedURLException, IOException {
        URL gmaps = new URL(url);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache-services.securitas.fi", 8080));
        HttpURLConnection c = (HttpURLConnection)gmaps.openConnection(proxy);
        c.connect();
        c.setConnectTimeout(5000);
        c.setReadTimeout(5000);
        InputSource inputXml = new InputSource(c.getInputStream());
        return inputXml;
    }
}
