-- Create the snippet table
CREATE TABLE snippet (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         language VARCHAR(255) NOT NULL,
                         code TEXT NOT NULL
);

-- Insert data into the snippet table
INSERT INTO snippet (language, code) VALUES ('Python', 'print(''Hello, World!'')');
INSERT INTO snippet (language, code) VALUES ('Python', 'def add(a, b):\n    return a + b');
INSERT INTO snippet (language, code) VALUES ('Python', 'class Circle:\n    def __init__(self, radius):\n        self.radius = radius\n\n    def area(self):\n        return 3.14 * self.radius ** 2');
INSERT INTO snippet (language, code) VALUES ('JavaScript', 'console.log(''Hello, World!'');');
INSERT INTO snippet (language, code) VALUES ('JavaScript', 'function multiply(a, b) {\n    return a * b;\n}');
INSERT INTO snippet (language, code) VALUES ('JavaScript', 'const square = num => num * num;');
INSERT INTO snippet (language, code) VALUES ('Java', 'public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}');
INSERT INTO snippet (language, code) VALUES ('Java', 'public class Rectangle {\n    private int width;\n    private int height;\n\n    public Rectangle(int width, int height) {\n        this.width = width;\n        this.height = height;\n    }\n\n    public int getArea() {\n        return width * height;\n    }\n}');