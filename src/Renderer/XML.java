
package Renderer;
import lighting.AmbientLight;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 *xmlReader
 */
public class XML {

    String fileName;
    Element root ;

    /**
     *the xmlReader
     */
    private  XML(String name){
        this.fileName  = name ;
        root =getRoot();
    }


    /**
     *get the root, according to choice
     */
    Element getRoot(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            final String FOLDER_PATH = System.getProperty("user.dir" ) ;
            File file = new File(FOLDER_PATH + '/' + fileName );
            try{

                Document document = builder.parse(file);
                document.getDocumentElement().normalize();
                return document.getDocumentElement();

            }
            catch(IOException e){
                throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
            }
            catch(SAXException e){
                throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
            }
        }
        catch (ParserConfigurationException e) {
            System.out.println("I dont realy care");
        }
        return null ;

    }
    /**
     *constructor with Element xmlAtDouble3
     */
    Double3 xmlDouble3(String name,Element root){
        String color  = root.getAttribute(name);
        String  [] colorarr= color.split(" ");
        return  new Double3(Double.parseDouble(colorarr[0]),Double.parseDouble(colorarr[1]),Double.parseDouble(colorarr[2]));
    }
    /**
     *constructor with Node xmlAtDouble3
     */
    Double3 xmlDouble3(String name,Node root){
        String color  = root.getAttributes().getNamedItem(name).getTextContent();
        String  [] colorarr= color.split(" ");
        return  new Double3(Double.parseDouble(colorarr[0]),Double.parseDouble(colorarr[1]),Double.parseDouble(colorarr[2]));
    }

    /**
     *constructor with Node xmlAtDouble3
     */
    Double3 xmlToAmbientDouble3(Node root){
        String color  = root.getAttributes().item(0).getTextContent();
        String  [] colorarr= color.split(" ");
        return  new Double3(Double.parseDouble(colorarr[0]),Double.parseDouble(colorarr[1]),Double.parseDouble(colorarr[2]));
    }


    /**
     * get color
     * @return color
     */
    public Color getBackground(){

        return new Color(xmlDouble3("background-color",root));
    }
    /**
     *simple ambient light
     */
    public AmbientLight getAmbient(){
        Node ambientLight = root.getElementsByTagName("ambient-light").item(0);
        return new AmbientLight(new Color(xmlToAmbientDouble3(ambientLight)),xmlDouble3("dx",ambientLight));

    }
    /**
     *get the geometries used
     */
    public Geometries getGeometries(){

        Geometries g = new Geometries();
        Node geo = root.getElementsByTagName("geometries").item(0);
        for(int i =0  ; i <  geo.getChildNodes().getLength() ; i++ ){
            Node x = geo.getChildNodes().item(i);
            if(x.getNodeName() == "triangle"){
                g.add(new Triangle(new Point(xmlDouble3("p0",x)), new Point(xmlDouble3("p1",x)) , new Point(xmlDouble3("p2",x))));
            }
            if(x.getNodeName() == "sphere"){
                g.add(new Sphere( new Point(xmlDouble3("center",x)) , Double.parseDouble(x.getAttributes().getNamedItem("radius").getTextContent())));
            }
            if(x.getNodeName() == "geometries"){
                g.add(getGeometries(x));
            }
        }
        return g ;
    }
    /**
     *get the geometries used and built with
     */
    public Geometries getGeometries(Node geo){
        Geometries g = new Geometries();
        for(int i =0  ; i <  geo.getChildNodes().getLength() ; i++ ){
            Node x = geo.getChildNodes().item(i);
            if(x.getNodeName() == "triangle"){
                g.add(new Triangle(new Point(xmlDouble3("p0",x)), new Point(xmlDouble3("p1",x)) , new Point(xmlDouble3("p2",x))));
            }
            if(x.getNodeName() == "sphere"){
                g.add(new Sphere( new Point(xmlDouble3("center",x)) , Double.parseDouble(x.getAttributes().getNamedItem("radius").getTextContent())));
            }
            if(x.getNodeName() == "geometries"){
                g.add(getGeometries(x));
            }
        }
        return g ;
    }

    public static Scene getScene(String path)
    {
        XML xml = new XML(path);
        Scene scene = new Scene.SceneBuilder("XML Test scene")
                //.setAmbientLight(xml.getAmbient())
                .setBackground(xml.getBackground())
                .build();
        // enter XML file name and parse from XML file into scene object
        // ...
        scene.geometries.add(xml.getGeometries());
        return scene;
    }

}