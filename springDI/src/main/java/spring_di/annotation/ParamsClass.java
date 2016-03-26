package spring_di.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("annotationParameters")
public class ParamsClass {
    @Resource(name = "byteValue")
    private byte aByte;
    @Resource(name = "shortValue")
    private short aShort;
    @Resource(name = "charValue")
    private char aChar;
    @Resource(name = "intValue")
    private int anInt;
    @Resource(name = "floatValue")
    private float aFloat;
    @Resource(name = "doubleValue")
    private double aDouble;
    @Resource(name = "booleanValue")
    private boolean aBoolean;
    @Resource(name = "stringValue")
    private String string;
    @Resource(name = "arrayValue")
    private int[] ints;
    @Resource(name = "listValue")
    private List<Double> doubles;
    @Resource(name = "setValue")
    private Set<Boolean> booleans;
    @Resource(name = "mapValue")
    private Map<Byte, Object> byteObjectMap;
    @Resource(name = "propertiesValue")
    private Properties properties;

    public ParamsClass(){}

    public ParamsClass(byte aByte, short aShort, char aChar, int anInt,
                       float aFloat, double aDouble, boolean aBoolean,
                       String string, int[] ints, List<Double> doubles,
                       Set<Boolean> booleans, Map<Byte, Object> byteObjectMap,
                       Properties properties) {
        this.aByte = aByte;
        this.aShort = aShort;
        this.aChar = aChar;
        this.anInt = anInt;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aBoolean = aBoolean;
        this.string = string;
        this.ints = ints;
        this.doubles = doubles;
        this.booleans = booleans;
        this.byteObjectMap = byteObjectMap;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ParamsClass{" + '\n' +
                "aByte=" + aByte + '\n' +
                ", aShort=" + aShort + '\n' +
                ", aChar=" + aChar + '\n' +
                ", anInt=" + anInt + '\n' +
                ", aFloat=" + aFloat + '\n' +
                ", aDouble=" + aDouble + '\n' +
                ", aBoolean=" + aBoolean + '\n' +
                ", string='" + string + '\'' + '\n' +
                ", ints=" + Arrays.toString(ints) + '\n' +
                ", doubles=" + doubles + '\n' +
                ", booleans=" + booleans + '\n' +
                ", byteObjectMap=" + byteObjectMap + '\n' +
                ", properties=" + properties + '\n' +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("app-context-annotation.xml");
        context.refresh();
        System.out.println(context.getBean("annotationParameters", ParamsClass.class));
    }

    public void setaByte(byte aByte) {
        this.aByte = aByte;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    public void setDoubles(List<Double> doubles) {
        this.doubles = doubles;
    }

    public void setBooleans(Set<Boolean> booleans) {
        this.booleans = booleans;
    }

    public void setByteObjectMap(Map<Byte, Object> byteObjectMap) {
        this.byteObjectMap = byteObjectMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public byte getaByte() {
        return aByte;
    }

    public short getaShort() {
        return aShort;
    }

    public char getaChar() {
        return aChar;
    }

    public int getAnInt() {
        return anInt;
    }

    public float getaFloat() {
        return aFloat;
    }

    public double getaDouble() {
        return aDouble;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public String getString() {
        return string;
    }

    public int[] getInts() {
        return ints;
    }

    public List<Double> getDoubles() {
        return doubles;
    }

    public Set<Boolean> getBooleans() {
        return booleans;
    }

    public Map<Byte, Object> getByteObjectMap() {
        return byteObjectMap;
    }

    public Properties getProperties() {
        return properties;
    }
}
