/*
 * This file is generated by jOOQ.
 */
package com.jobster.server.model.tables.records;


import com.jobster.server.model.tables.OfferFormation;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class OfferFormationRecord extends UpdatableRecordImpl<OfferFormationRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = -1654274796;

    /**
     * Setter for <code>jobster.offer_formation.id_offer_formation</code>.
     */
    public void setIdOfferFormation(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>jobster.offer_formation.id_offer_formation</code>.
     */
    public Integer getIdOfferFormation() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>jobster.offer_formation.id_offer</code>.
     */
    public void setIdOffer(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>jobster.offer_formation.id_offer</code>.
     */
    public Integer getIdOffer() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>jobster.offer_formation.id_formation</code>.
     */
    public void setIdFormation(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>jobster.offer_formation.id_formation</code>.
     */
    public Integer getIdFormation() {
        return (Integer) get(2);
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
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return OfferFormation.OFFER_FORMATION.ID_OFFER_FORMATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return OfferFormation.OFFER_FORMATION.ID_OFFER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return OfferFormation.OFFER_FORMATION.ID_FORMATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getIdOfferFormation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getIdOffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getIdFormation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdOfferFormation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getIdOffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getIdFormation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferFormationRecord value1(Integer value) {
        setIdOfferFormation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferFormationRecord value2(Integer value) {
        setIdOffer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferFormationRecord value3(Integer value) {
        setIdFormation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfferFormationRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OfferFormationRecord
     */
    public OfferFormationRecord() {
        super(OfferFormation.OFFER_FORMATION);
    }

    /**
     * Create a detached, initialised OfferFormationRecord
     */
    public OfferFormationRecord(Integer idOfferFormation, Integer idOffer, Integer idFormation) {
        super(OfferFormation.OFFER_FORMATION);

        set(0, idOfferFormation);
        set(1, idOffer);
        set(2, idFormation);
    }
}
