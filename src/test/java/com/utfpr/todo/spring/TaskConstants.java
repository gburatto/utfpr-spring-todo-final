package com.utfpr.todo.spring;

import java.time.LocalDateTime;
import java.util.UUID;

import com.utfpr.todo.clean.infra.model.TaskModel;
import com.utfpr.todo.tasks.TaskInputDTO;
import com.utfpr.todo.tasks.TaskOutputDTO;
import com.utfpr.todo.users.UserConstants;

public class TaskConstants {

        private static final String TITLE = "Test";

        private static final String DESCRIPTION = "Test";

        private static final boolean COMPLETED = false;

        private static final String PRIORITY = "low";

        private static final LocalDateTime START_AT = LocalDateTime.now().plusDays(2);

        private static final LocalDateTime END_AT = LocalDateTime.now().plusDays(3);

        private static final LocalDateTime YESTERDAY = LocalDateTime.now().minusDays(1);

        public static final TaskInputDTO TASK_INPUT_DTO = TaskInputDTO.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(END_AT)
                        .build();

        public static final TaskModel TASK = TaskModel.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .completed(COMPLETED)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(END_AT)
                        .build();

        public static final TaskModel TASK_CREATED = TaskModel.builder()
                        .id(UUID.randomUUID().toString())
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .completed(COMPLETED)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(END_AT)
                        .build();

        public static final TaskModel TASK_EMPTY = TaskModel.builder().build();

        public static final TaskModel TASK_INVALID = TaskModel.builder()
                        .title("")
                        .description("")
                        .completed(false)
                        .priority("")
                        .build();
        
        public static final TaskInputDTO TASK_INVALID_END_AT_DATE = TaskInputDTO.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(YESTERDAY)
                        .build();

        public static final TaskInputDTO TASK_INVALID_START_AT_DATE = TaskInputDTO.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .priority(PRIORITY)
                        .startAt(YESTERDAY)
                        .endAt(END_AT)
                        .build();

        public static final TaskOutputDTO TASK_OUTPUT_DTO = TaskOutputDTO.builder()
                        .id(UUID.randomUUID().toString())
                        .userId(UserConstants.USER_ID)
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .completed(COMPLETED)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(END_AT)
                        .build();

}
