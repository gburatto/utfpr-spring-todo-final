package com.utfpr.todo.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskConstants {

        private static final String TITLE = "Test";

        private static final String DESCRIPTION = "Test";

        private static final boolean COMPLETED = false;

        private static final String PRIORITY = "low";

        private static final LocalDateTime START_AT = LocalDateTime.now().plusDays(2);

        private static final LocalDateTime END_AT = LocalDateTime.now().plusDays(3);

        private static final LocalDateTime YESTERDAY = LocalDateTime.now().minusDays(1);

        public static final TaskModel TASK_INVALID_END_AT_DATE = TaskModel.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .completed(COMPLETED)
                        .priority(PRIORITY)
                        .startAt(START_AT)
                        .endAt(YESTERDAY)
                        .build();

        public static final TaskModel TASK_INVALID_START_AT_DATE = TaskModel.builder()
                        .title(TITLE)
                        .description(DESCRIPTION)
                        .completed(COMPLETED)
                        .priority(PRIORITY)
                        .startAt(YESTERDAY)
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

}
