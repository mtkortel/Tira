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
import javax.xml.soap.Node;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Googlen Distance Matrix haku ja parserointi
 * @author Marko Kortelainen
 * @see https://developers.google.com/maps/documentation/distancematrix/
 */
public class DistanceMatrix {
    private final String gmaps1 = "http://maps.googleapis.com/maps/api/distancematrix/xml?origins=";
    private final String gmaps2 = "&destinations=";
    private final String gmaps3 = "&language=fi-FI&sensor=false";
    XPathFactory factory;
    XPath xpath;
    
    /**
     * Konstruktori
     */
    public DistanceMatrix (){
        factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
    }
    
    public void getMatrix(TaulukkoLista<ReittiPiste> lista){
        NodeList nodes = getDistance(lista);
        for(int i = 0; i < nodes.getLength(); i++){
            System.out.println(nodes.item(i).getTextContent());
        }
       
    }

    private NodeList getDistance(TaulukkoLista<ReittiPiste> lista) {
        String start = "";
        String dest = "";
        for (int i = 0; i < lista.size; i++){
            ReittiPiste rp = lista.get(i);
            start+= rp.getNimi().replaceAll(" ", "+");
            dest+= rp.getNimi().replaceAll(" ", "+");
            if (i < lista.size-1){
                start+="|";
                dest+="|";
            }
        }
        String cmd = gmaps1 + start + gmaps2 + dest + gmaps3;
        try {
            System.setProperty("http.proxyHost", "cache-services.securitas.fi");
            System.setProperty("http.proxyPort", "8080");
            return getDists(cmd);
        } catch (Exception ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private NodeList getDists(String cmd) throws MalformedURLException, IOException, XPathExpressionException {
        InputSource inputXml = getXML(cmd);
        String xpathExpression = "/DistanceMatrixResponse/row/element/distance/text";
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
        System.out.println(url);
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
