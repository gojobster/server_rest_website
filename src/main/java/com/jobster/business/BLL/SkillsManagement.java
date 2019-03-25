package com.jobster.business.BLL;

import com.jobster.server.model.tables.records.SkillsRecord;
import com.jobster.business.types.JobsterErrorType;

import java.util.List;

import static com.jobster.server.model.Tables.SKILLS;

public class SkillsManagement {
    public static String addSkill(String skillName) throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        skillName = skillName.trim();

        SkillsRecord idiom = connection.create.select()
                .from(SKILLS)
                .where(SKILLS.NAME.equal(skillName))
                .fetchAnyInto(SkillsRecord.class);
        if (idiom != null) throw new JobsterException(JobsterErrorType.SKILL_ALREADY_EXISTS);

        SkillsRecord skill = connection.create.newRecord(SKILLS);
        skill.setName(skillName);

        skill.store();
        connection.closeConnection();
        return "OK";
    }

    public static List<String> getAllSkills() throws JobsterException {
        ConnectionBDManager connection = new ConnectionBDManager();

        List<String> lst_skills = connection.create.select().from(SKILLS).fetch(SKILLS.NAME);

        connection.closeConnection();
        return lst_skills;
    }
}
