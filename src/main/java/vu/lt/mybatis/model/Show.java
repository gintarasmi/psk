package vu.lt.mybatis.model;

public class Show {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOW.ID
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.SHOW.NAME
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOW.ID
     *
     * @return the value of PUBLIC.SHOW.ID
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOW.ID
     *
     * @param id the value for PUBLIC.SHOW.ID
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.SHOW.NAME
     *
     * @return the value of PUBLIC.SHOW.NAME
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.SHOW.NAME
     *
     * @param name the value for PUBLIC.SHOW.NAME
     *
     * @mbg.generated Tue Apr 27 12:15:51 EEST 2021
     */
    public void setName(String name) {
        this.name = name;
    }
}