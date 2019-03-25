/*
 * This file is generated by jOOQ.
 */
package com.jobster.server.model.tables.records;


import com.jobster.server.model.tables.Referrals;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
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
public class ReferralsRecord extends UpdatableRecordImpl<ReferralsRecord> implements Record10<Integer, Integer, Integer, Integer, Integer, String, Integer, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1482071633;

    /**
     * Setter for <code>jobster.referrals.id_referral</code>.
     */
    public void setIdReferral(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>jobster.referrals.id_referral</code>.
     */
    public Integer getIdReferral() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>jobster.referrals.id_jobster</code>.
     */
    public void setIdJobster(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>jobster.referrals.id_jobster</code>.
     */
    public Integer getIdJobster() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>jobster.referrals.id_candidate</code>.
     */
    public void setIdCandidate(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>jobster.referrals.id_candidate</code>.
     */
    public Integer getIdCandidate() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>jobster.referrals.score</code>.
     */
    public void setScore(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>jobster.referrals.score</code>.
     */
    public Integer getScore() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>jobster.referrals.state</code>.
     */
    public void setState(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>jobster.referrals.state</code>.
     */
    public Integer getState() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>jobster.referrals.code</code>.
     */
    public void setCode(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>jobster.referrals.code</code>.
     */
    public String getCode() {
        return (String) get(5);
    }

    /**
     * Setter for <code>jobster.referrals.id_offer</code>.
     */
    public void setIdOffer(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>jobster.referrals.id_offer</code>.
     */
    public Integer getIdOffer() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>jobster.referrals.email_candidate</code>.
     */
    public void setEmailCandidate(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>jobster.referrals.email_candidate</code>.
     */
    public String getEmailCandidate() {
        return (String) get(7);
    }

    /**
     * Setter for <code>jobster.referrals.date_creation</code>.
     */
    public void setDateCreation(Timestamp value) {
        set(8, value);
    }

    /**
     * Getter for <code>jobster.referrals.date_creation</code>.
     */
    public Timestamp getDateCreation() {
        return (Timestamp) get(8);
    }

    /**
     * Setter for <code>jobster.referrals.date_accepted</code>.
     */
    public void setDateAccepted(Timestamp value) {
        set(9, value);
    }

    /**
     * Getter for <code>jobster.referrals.date_accepted</code>.
     */
    public Timestamp getDateAccepted() {
        return (Timestamp) get(9);
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
    // Record10 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, Integer, Integer, Integer, Integer, String, Integer, String, Timestamp, Timestamp> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row10<Integer, Integer, Integer, Integer, Integer, String, Integer, String, Timestamp, Timestamp> valuesRow() {
        return (Row10) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Referrals.REFERRALS.ID_REFERRAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Referrals.REFERRALS.ID_JOBSTER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Referrals.REFERRALS.ID_CANDIDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Referrals.REFERRALS.SCORE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Referrals.REFERRALS.STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Referrals.REFERRALS.CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Referrals.REFERRALS.ID_OFFER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Referrals.REFERRALS.EMAIL_CANDIDATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field9() {
        return Referrals.REFERRALS.DATE_CREATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field10() {
        return Referrals.REFERRALS.DATE_ACCEPTED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getIdReferral();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getIdJobster();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component3() {
        return getIdCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component7() {
        return getIdOffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getEmailCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component9() {
        return getDateCreation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component10() {
        return getDateAccepted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdReferral();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getIdJobster();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getIdCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getIdOffer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getEmailCandidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value9() {
        return getDateCreation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value10() {
        return getDateAccepted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value1(Integer value) {
        setIdReferral(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value2(Integer value) {
        setIdJobster(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value3(Integer value) {
        setIdCandidate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value4(Integer value) {
        setScore(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value5(Integer value) {
        setState(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value6(String value) {
        setCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value7(Integer value) {
        setIdOffer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value8(String value) {
        setEmailCandidate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value9(Timestamp value) {
        setDateCreation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord value10(Timestamp value) {
        setDateAccepted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferralsRecord values(Integer value1, Integer value2, Integer value3, Integer value4, Integer value5, String value6, Integer value7, String value8, Timestamp value9, Timestamp value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ReferralsRecord
     */
    public ReferralsRecord() {
        super(Referrals.REFERRALS);
    }

    /**
     * Create a detached, initialised ReferralsRecord
     */
    public ReferralsRecord(Integer idReferral, Integer idJobster, Integer idCandidate, Integer score, Integer state, String code, Integer idOffer, String emailCandidate, Timestamp dateCreation, Timestamp dateAccepted) {
        super(Referrals.REFERRALS);

        set(0, idReferral);
        set(1, idJobster);
        set(2, idCandidate);
        set(3, score);
        set(4, state);
        set(5, code);
        set(6, idOffer);
        set(7, emailCandidate);
        set(8, dateCreation);
        set(9, dateAccepted);
    }
}
