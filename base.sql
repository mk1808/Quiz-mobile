INSERT INTO `user` (`id`, `course`, `email`, `name`, `password`, `surname`, `username`) VALUES ('1', NULL, 'login1@gmail.com', 'Mm', 'login1', 'Kk', 'login1');
INSERT INTO `user` (`id`, `course`, `email`, `name`, `password`, `surname`, `username`) VALUES ('2', NULL, 'login2@gmail.com', 'Mm', 'login2', 'Kk', 'login2');
INSERT INTO `user` (`id`, `course`, `email`, `name`, `password`, `surname`, `username`) VALUES ('3', NULL, 'login3@gmail.com', 'Mm', 'login3n', 'Kk', 'login3n');

INSERT INTO `user_roles` (`role_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `user_roles` (`role_id`, `user_id`) VALUES ('1', '2');
INSERT INTO `user_roles` (`role_id`, `user_id`) VALUES ('2', '3');

INSERT INTO `subject` (`id`, `can_back`, `course`, `description`, `limited_time`, `multiple_choice`, `name`, `no_questions`, `randomize`, `separate_page`, `subject`, `time`) VALUES ('1', b'1', "demojava", NULL, b'1', b'1', 'quiz1', '6', b'0', b'1', 'web', '10');
INSERT INTO `subject` (`id`, `can_back`, `course`, `description`, `limited_time`, `multiple_choice`, `name`, `no_questions`, `randomize`, `separate_page`, `subject`, `time`) VALUES ('2', b'1', NULL, NULL, b'1', b'0', 'quiz2', '10', b'0', b'1', 'java', '20');

INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('1', NULL, NULL, 'pytanie1', '1');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('2', NULL, NULL, 'pytanie2', '1');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('3', NULL, NULL, 'pytanie3', '1');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('4', NULL, NULL, 'pytanie21', '2');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('5', NULL, NULL, 'pytanie22', '2');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('6', NULL, NULL, 'pytanie23', '2');
INSERT INTO `question` (`id`, `code`, `image`, `text`, `subject_id`) VALUES ('7', NULL, NULL, 'pytanie24', '2');

INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('1', b'1', 'odpA', '1');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('2', b'1', 'odpB', '1');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('3', b'0', 'odpC', '1');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('4', b'0', 'odpD', '1');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('5', b'1', 'odpA', '2');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('6', b'0', 'odpB', '2');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('7', b'1', 'odpC', '2');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('8', b'1', 'odpD', '2');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('9', b'0', 'odpA', '3');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('10', b'1', 'odpB', '3');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('11', b'0', 'odpC', '3');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('12', b'1', 'odpD', '3');

INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('13', b'1', 'odpA', '4');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('14', b'0', 'odpB', '4');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('15', b'0', 'odpC', '4');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('16', b'0', 'odpD', '4');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('17', b'0', 'odpA', '5');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('18', b'0', 'odpB', '5');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('19', b'1', 'odpC', '5');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('20', b'0', 'odpD', '5');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('21', b'0', 'odpA', '6');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('22', b'1', 'odpB', '6');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('23', b'0', 'odpC', '6');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('24', b'0', 'odpD', '6');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('25', b'0', 'odpA', '7');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('26', b'1', 'odpB', '7');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('27', b'0', 'odpC', '7');
INSERT INTO `answer` (`id`, `status`, `text`, `question_id`) VALUES ('28', b'0', 'odpD', '7');

INSERT INTO `user_result` (`id`, `result`, `subject_id`, `user_id`) VALUES ('1', '0.1', '1', '1');
INSERT INTO `user_result` (`id`, `result`, `subject_id`, `user_id`) VALUES ('2', '0.9', '2', '1');
INSERT INTO `user_result` (`id`, `result`, `subject_id`, `user_id`) VALUES ('3', '0.76', '1', '2');
INSERT INTO `user_result` (`id`, `result`, `subject_id`, `user_id`) VALUES ('4', '0.44', '2', '2');

