/*
 * This file is generated by jOOQ.
 */
package com.jobster.server.model.tables.records;


import com.jobster.server.model.tables.Countries;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CountriesRecord extends UpdatableRecordImpl<CountriesRecord> implements Record6<Integer, String, String, String, String, String> {

    private static final long serialVersionUID = -1287068021;

    /**
     * Setter for <code>jobster.countries.id_country</code>.
     */
    public void setIdCountry(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>jobster.countries.id_country</code>.
     */
    public Integer getIdCountry() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>jobster.countries.name_es</code>.
     */
    public void setNameEs(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>jobster.countries.name_es</code>.
     */
    public String getNameEs() {
        return (String) get(1);
    }

    /**
     * Setter for <code>jobster.countries.name_en</code>.
     */
    public void setNameEn(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>jobster.countries.name_en</code>.
     */
    public String getNameEn() {
        return (String) get(2);
    }

    /**
     * Setter for <code>jobster.countries.name_it</code>.
     */
    public void setNameIt(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>jobster.countries.name_it</code>.
     */
    public String getNameIt() {
        return (String) get(3);
    }

    /**
     * Setter for <code>jobster.countries.name_fr</code>.
     */
    public void setNameFr(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>jobster.countries.name_fr</code>.
     */
    public String getNameFr() {
        return (String) get(4);
    }

    /**
     * Setter for <code>jobster.countries.name_de</code>.
     */
    public void setNameDe(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>jobster.countries.name_de</code>.
     */
    public String getNameDe() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Countries.COUNTRIES.ID_COUNTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Countries.COUNTRIES.NAME_ES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Countries.COUNTRIES.NAME_EN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Countries.COUNTRIES.NAME_IT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Countries.COUNTRIES.NAME_FR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Countries.COUNTRIES.NAME_DE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getIdCountry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNameEs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getNameEn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getNameIt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getNameFr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getNameDe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdCountry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNameEs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getNameEn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getNameIt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getNameFr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getNameDe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value1(Integer value) {
        setIdCountry(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value2(String value) {
        setNameEs(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value3(String value) {
        setNameEn(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value4(String value) {
        setNameIt(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value5(String value) {
        setNameFr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord value6(String value) {
        setNameDe(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountriesRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CountriesRecord
     */
    public CountriesRecord() {
        super(Countries.COUNTRIES);
    }

    /**
     * Create a detached, initialised CountriesRecord
     */
    public CountriesRecord(Integer idCountry, String nameEs, String nameEn, String nameIt, String nameFr, String nameDe) {
        super(Countries.COUNTRIES);

        set(0, idCountry);
        set(1, nameEs);
        set(2, nameEn);
        set(3, nameIt);
        set(4, nameFr);
        set(5, nameDe);
    }
}
