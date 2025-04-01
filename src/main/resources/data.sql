-- Create the users table
CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- Create the snippet table
CREATE TABLE snippet (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         language VARCHAR(255) NOT NULL,
                         code TEXT NOT NULL,
                         user_id UUID NOT NULL, -- Foreign key to the users table
                         CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert a single user into the users table
INSERT INTO users (id, username, password) VALUES
    ('11111111-1111-1111-1111-111111111111', 'john_doe', 'securepassword');

-- Insert data into the snippet table, all belonging to the same user
INSERT INTO snippet (language, code, user_id) VALUES
                                                  ('Python', 'F6RVvB9M0kTp4c19cp1LlM3QzRiWwvXb/KFuVobMZOE=', '11111111-1111-1111-1111-111111111111'),
                                                  ('Python', 'eUy6pPPPrk7LnN+UN/Fpk5ae+RqpVhfxsy7bAJxUcSfZKP5imWpAAt+6XfQx51wr', '11111111-1111-1111-1111-111111111111'),
                                                  ('Python', 'NsFc1ycGXZu3iSpUqSWXkcTpzuNWpid09Fn3XuESlXIL9DV0muLnmV08sWJriDtgg9JQvzWuXH5hNyfRK85bF3qIsh82yDgfRmsg2oONoc7TmVmVDQ5E0dr0CM1QJs3Ii3aeEvKiiopC/uBdul7NTWh5f3HqbC7r1b/h2OJsLXRW9P773utyJcgLjBus+dAH', '11111111-1111-1111-1111-111111111111'),
                                                  ('JavaScript', 'PiF69yFYBt2TV2Q35npHc/2futbEJ6NLgYhxg8Ufs54=', '11111111-1111-1111-1111-111111111111'),
                                                  ('JavaScript', 'SL/6zBlSQZbwW3UjWXxk90odk3+xoIIhhsNMLgv+5MX1O3V0NY/K1NF/UAXwVoZc', '11111111-1111-1111-1111-111111111111'),
                                                  ('JavaScript', 'Hk0oXwuqmSCzVrroYWQg208ahL+WRcMn65O7uXbufK3ZKP5imWpAAt+6XfQx51wr', '11111111-1111-1111-1111-111111111111'),
                                                  ('Java', 'WSFieDbTsTxQdfEr22BKHd5DQFaHTFfM6IL92IftpJS30RXcdPFGHDj29rumUTHClncFsaX+v0DoBAsKfLFo6ulAvLlxIxmnojn9lEuJO3zG/WopaCcZoSFIJMzBPyrlcIFPbzlehbN1W15aHBk/kKOCyabnSFS13LhgMXizrO4=', '11111111-1111-1111-1111-111111111111'),
                                                  ('Java', 'a8ppheI5Cm6slpmCp28VirKyInKsQMwB6FOT/G2Y0ssIACmvpWGp4laqrtvWZX/tPMRyNSZBQFq7+zJJyQIj97VtzCVVARmQlLefK558MJmI7blxGvpq1p0GsQz8hSs2ZlyPVOrIPA+w9SwhXL6lbGQJ0eYKqNi9gT4ePtPiWo5o8kMayiRAnObW5uYu0ynfQ8YOSnyxXpQbyIRTLJ5XLZqVviehotjBQhQFIOVZzjwfF7JPDBc3GI4JzzMCdrSb/bmUkxqHEMG3ICYTYi+Saxgpcn6mOtoYfUIZsc6GxxOsOjboO0bp30b7FsuIRObIjCzJTH4yY7XmKbGEJ00ypA==', '11111111-1111-1111-1111-111111111111');