/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.aguacate.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.aguacate.conector.Conexion;

/**
 *
 * @author Barush
 */
public class Paises {

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the surfacearea
     */
    public double getSurfacearea() {
        return surfacearea;
    }

    /**
     * @param surfacearea the surfacearea to set
     */
    public void setSurfacearea(double surfacearea) {
        this.surfacearea = surfacearea;
    }

    /**
     * @return the indepyear
     */
    public int getIndepyear() {
        return indepyear;
    }

    /**
     * @param indepyear the indepyear to set
     */
    public void setIndepyear(int indepyear) {
        this.indepyear = indepyear;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the lifeexpectancy
     */
    public double getLifeexpectancy() {
        return lifeexpectancy;
    }

    /**
     * @param lifeexpectancy the lifeexpectancy to set
     */
    public void setLifeexpectancy(double lifeexpectancy) {
        this.lifeexpectancy = lifeexpectancy;
    }

    /**
     * @return the gnp
     */
    public double getGnp() {
        return gnp;
    }

    /**
     * @param gnp the gnp to set
     */
    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    /**
     * @return the localname
     */
    public String getLocalname() {
        return localname;
    }

    /**
     * @param localname the localname to set
     */
    public void setLocalname(String localname) {
        this.localname = localname;
    }

    /**
     * @return the governmentform
     */
    public String getGovernmentform() {
        return governmentform;
    }

    /**
     * @param governmentform the governmentform to set
     */
    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform;
    }

    /**
     * @return the headofstate
     */
    public String getHeadofstate() {
        return headofstate;
    }

    /**
     * @param headofstate the headofstate to set
     */
    public void setHeadofstate(String headofstate) {
        this.headofstate = headofstate;
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @return the code2
     */
    public String getCode2() {
        return code2;
    }

    /**
     * @param code2 the code2 to set
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public static List<Paises> getAll() {
        List<Paises> paises = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select code, name, continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, localname, capital, governmentform, code2 from country;");
            while (rs.next()) {
                Paises r = new Paises();
                r.setCode(rs.getString(1));
                r.setName(rs.getString(2));
                r.setContinent(rs.getString(3));
                r.setRegion(rs.getString(4));
                r.setSurfacearea(rs.getDouble(5));
                r.setIndepyear(rs.getInt(6));
                r.setPopulation(rs.getInt(7));
                r.setLifeexpectancy(rs.getDouble(8));
                r.setGnp(rs.getDouble(9));
                r.setLocalname(rs.getString(10));
                r.setCapital(rs.getString(11));
                r.setGovernmentform(rs.getString(12));
                r.setCode2(rs.getString(13));
                paises.add(r);
            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return paises;
    }

    public static boolean edit(String code, int nuevaPoblacion) {
        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "UPDATE country SET population = ? WHERE code = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, nuevaPoblacion); // Primer parámetro: población
            statement.setString(2, code);       // Segundo parámetro: código
            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Ocurrió un error al actualizar la población: " + ex.getMessage());
        }
        return resultado;
    }

    public static boolean delete(String code) {
        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "DELETE FROM country WHERE code = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, code);

            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
    //UPDATE `world`.`country` SET `Code` = 'ATFm', `Name` = 'French Southern territoriess', `Continent` = 'Antarcticas', `Region` = 'Antarcticas', `SurfaceArea` = '17780.00', `Population` = '1', `LifeExpectancy` = '1', `GNP` = '1', `GNPOld` = '1', `LocalName` = 'Terres australes françaisess', `GovernmentForm` = 'Nonmetropolitan Territory of Frances', `HeadOfState` = 'Jacques Chiracs', `Capital` = '1', `Code2` = 'TFc' WHERE (`Code` = 'ATF');

    public static boolean add(String code, String name, String continent,
            String region, double surfaceArea, int indepYear, int population,
            double lifeExpectancy, double gnp,
            String localName, String governmentForm, String capital, String code2) {

        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "INSERT INTO country (code, name, continent, region, surfacearea, indepYear, population, lifeexpectancy, gnp, localName, governmentForm, capital, code2) VALUES (?, ?, ?, ? ,? ,? ,? ,? ,? ,? , ?, ?, ? )";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, code);
            statement.setString(2, name);
            statement.setString(3, continent);
            statement.setString(4, region);
            statement.setDouble(5, surfaceArea);
            statement.setInt(6, indepYear);
            statement.setInt(7, population);
            statement.setDouble(8, lifeExpectancy);
            statement.setDouble(9, gnp);
            statement.setString(10, localName);
            statement.setString(11, governmentForm);
            statement.setString(12, capital);
            statement.setString(13, code2);
            
            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
            
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }

    private String code;
    private String name;
    private String continent;
    private String region;
    private double surfacearea;
    private int indepyear;
    private int population;
    private double lifeexpectancy;
    private double gnp;
    private String localname;
    private String governmentform;
    private String headofstate;
    private String capital;
    private String code2;
}
