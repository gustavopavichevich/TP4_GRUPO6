package com.example.myapplication.Conexion;

public class DataBD {

    //Información de la BD
    public static String host = "ckshdphy86qnz0bj.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";
    public static String port = "3306";
    public static String nameBD = "qws5z4k23ylalexf";
    public static String user = "qbfo0imrrreg3ary";
    public static String pass = "upwzu48g54qce9df";

    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/" + nameBD;
    public static String driver = "com.mysql.jdbc.Driver";
}





