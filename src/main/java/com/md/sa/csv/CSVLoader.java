package com.md.sa.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.md.sa.facade.dto.StudentData;
import com.md.sa.facade.dto.SubjectData;
import com.md.sa.model.Groups;
import com.md.sa.model.Lecturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;


@Component
public class CSVLoader {
    private static Logger LOG = LoggerFactory.getLogger(CSVLoader.class);

    private <T> List<T> loadObjectList(Class<T> type, File file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            LOG.error("Error occurred while loading object list from file " + file.getName(), e);
            return Collections.emptyList();
        }
    }

    public List<StudentData> getStudents(File file) {
        return loadObjectList(StudentData.class, file);
    }

    public List<Lecturer> getLecturers(File file) {
        return loadObjectList(Lecturer.class, file);
    }

    public List<SubjectData> getSubjects(File file) {
        return loadObjectList(SubjectData.class, file);
    }

    public List<Groups> getGroups(File file) {
        return loadObjectList(Groups.class, file);
    }
}
