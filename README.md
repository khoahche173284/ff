--CREATE DATABASE SWP391
go
USE SWP391
go
CREATE TABLE Activity_Type (
    ID INT PRIMARY KEY,
    ActivityName NVARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO Activity_Type (ID, ActivityName)
VALUES 
    (1, 'Login'), 
    (2, 'Logout'), 
    (3, 'Create Post'), 
    (4, 'Delete Post'), 
    (5, 'Comment'), 
    (6, 'Like Post'),
    (7, 'Change Avatar'), 
    (8, 'Update Infomation'), 
    (9, 'Delete Account'),
    (10, 'Change PassWord'),
    (11, 'Save Text'),
    (12, 'Check Text'),
    (13, 'Delete Draft'),
    (14, 'Restore Trash'),
    (15, 'Delete Trash'),
    (16, 'Upgrade Account'),
    (17, 'Check Grammar'),
    (18, 'Comment on any post'), 
    (19, 'Save Favorite'), 
    (20, 'Download File');
 

-- Tạo bảng Users
CREATE TABLE Users (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL, 
    Realname NVARCHAR(50) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    [Password] NVARCHAR(255) NOT NULL, 
    Img NVARCHAR(255) NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    RoleID INT
    
);

-- Tạo bảng RecentActivity
CREATE TABLE Recent_Activity (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ActivityTypeID INT NOT NULL,
    UserID INT NOT NULL,
    LoginTime DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Recent_Activity_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_Recent_Activity_ActivityType FOREIGN KEY (ActivityTypeID) REFERENCES Activity_Type(ID)
);




-- Tạo bảng Role
CREATE TABLE Role (
    ID INT NOT NULL PRIMARY KEY,
    Role_name NVARCHAR(50) NOT NULL UNIQUE
);

-- Thêm khóa ngoại cho bảng Users sau khi tạo bảng Role
ALTER TABLE Users 
ADD CONSTRAINT FK_Users_Role 
FOREIGN KEY (RoleID) REFERENCES Role(ID);

-- Thêm FailedLogin để kiểm tra số lần đăng nhập sai  Islocked là để khóa tài khoản.
ALTER TABLE Users
ADD FailedLoginAttempts INT DEFAULT 0,
    IsLocked BIT DEFAULT 0;

ALTER TABLE Users
ADD LockTime DATETIME;


-- Tạo bảng Categories
CREATE TABLE Categories (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    CategoriesName NVARCHAR(50) NOT NULL UNIQUE,
    Description NVARCHAR(255)
);

-- Tạo bảng Essay
CREATE TABLE Essay (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    CategoriesID INT NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Essay_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_Essay_Categories FOREIGN KEY (CategoriesID) REFERENCES Categories(ID)
);

-- Tạo bảng Draft
CREATE TABLE Draft (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    EssayID INT NOT NULL,
    LastTimeChange DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Draft_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_Draft_Essay FOREIGN KEY (EssayID) REFERENCES Essay(ID)
);

-- Tạo bảng Trash
CREATE TABLE Trash (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    EssayID INT NOT NULL,
    Deleted_Date DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Trash_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_Trash_Essay FOREIGN KEY (EssayID) REFERENCES Essay(ID)
);

-- Tạo bảng Feedback_Type
CREATE TABLE Feedback_Type (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Feedback_Name NVARCHAR(MAX) NOT NULL
);

-- Tạo bảng Feedback_For_System
CREATE TABLE Feedback_For_System (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    Feedback_TypeID INT NOT NULL,
    Content NVARCHAR(MAX) NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(), 
    Checked BIT NOT NULL DEFAULT 0,
    CONSTRAINT FK_Feedback_System_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_Feedback_System_Type FOREIGN KEY (Feedback_TypeID) REFERENCES Feedback_Type(ID)
);

-- Tạo bảng Product
CREATE TABLE Premium_Package
 (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ProductName NVARCHAR(100),
    Price INT NOT NULL,
    Description NVARCHAR(255)
);

-- Tạo bảng OrderDetail
CREATE TABLE OrderDetail (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    OrderDate DATETIME NOT NULL DEFAULT GETDATE(), -- Ngày đặt hàng
    UserID INT NOT NULL,
    ProductID INT,
	Email NVARCHAR(100) UNIQUE NOT NULL,
    Checked BIT NOT NULL, -- Tình trạng đơn hàng
    Amount DECIMAL(18, 2), -- Số tiền
    TransactionStatus NVARCHAR(50), -- Tình trạng giao dịch
	vnp_TxnRef NVARCHAR(50), --Mã giao dịch
	vnp_OrderInfo NVARCHAR(225), -- Mô tả giao dịch
	vnp_ResponseCode NVARCHAR(10), -- Mã lỗi thanh toán
    vnp_TransactionNo NVARCHAR(50), -- Mã giao dịch VNPAY
    vnp_BankCode NVARCHAR(50), -- Mã ngân hàng thanh toán
    vnp_PayDate DATETIME, -- Thời gian thanh toán
    CONSTRAINT FK_OrderDetail_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (ProductID) REFERENCES Product(ID)
);

-- Tạo bảng remember_me_tokens
CREATE TABLE Remember_Me_Tokens (
    user_id INT NOT NULL,
    token NVARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT FK_remember_me_tokens_Users FOREIGN KEY (user_id) REFERENCES Users(ID)
);

-- Tạo bảng library
CREATE TABLE Dictionary (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    keyword NVARCHAR(50) UNIQUE NOT NULL, 
    define NVARCHAR(255) NOT NULL, 
    UserID INT NOT NULL,
    CONSTRAINT FK_Lib_Users FOREIGN KEY (UserID) REFERENCES Users(ID)
);


CREATE TABLE Favorite_Essay_Draft (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    EssayID INT NOT NULL,
    addedDate DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_FavoriteEssayDraft_Users FOREIGN KEY (UserID) REFERENCES Users(ID),
    CONSTRAINT FK_FavoriteEssayDraft_Essay FOREIGN KEY (EssayID) REFERENCES Essay(ID)
);

ALTER TABLE OrderDetail
ADD CheckedDate DATETIME;

ALTER TABLE OrderDetail
ADD ExpirationDate DATE;

ALTER TABLE Product
ADD Duration INT;

-- Thêm dữ liệu vào bảng Role
INSERT INTO Role (ID, Role_name)
VALUES 
    (1, 'Admin'),
    (2, 'User'),
    (3, 'User VIP');

-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (Username, Realname, Email, [Password], Img, RoleID)
VALUES 
    ('Quang', 'Quang Quăn Queo', 'admin@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 1),
    ('Bao', 'Bảo Bảnh Bao', 'user1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 3),
    ('Tu', 'Tú Tí Tởn', 'user2@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2),
    ('Khoa', 'Khoa Khù Khoằm', 'editor1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 3),
    ('Hung', 'Hưng Hống Hách', 'reviewer1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2),
    ('Vinh', 'Vinh Vất Vưởng', 'manager1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 3),
    ('Anh', 'Anh', 'support1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2),
    ('Duy', 'Duy', 'contributor1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2),
    ('Hoang', 'Hoàng', 'moderator1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2),
    ('Linh', 'Linh', 'guest1@example.com', '123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq5D2o1F9Uf3L0BvK7kMOUdgzfgoxML2DenhpWSFZAzA&s', 2);

-- Thêm dữ liệu vào bảng Categories
INSERT INTO Categories (CategoriesName, Description)
VALUES 
    ('Essay', 'Essay'),
    ('Sentence', 'Sentence'),
    ('Summary', 'Summary');

-- Thêm dữ liệu vào bảng Essay
INSERT INTO Essay (UserID, CategoriesID, Content)
VALUES 
    (1, 1, 'The quick brown fox jumps over the lazy dog. Summary: This sentence is often used as a typing exercise.'),
    (2, 2, 'A journey of a thousand miles begins with a single step. Summary: This proverb emphasizes the importance of starting.'),
    (3, 3, 'To be or not to be, that is the question. Summary: This is a famous line from Shakespeare');

-- Thêm dữ liệu vào bảng Draft
INSERT INTO Draft (UserID, EssayID)
VALUES 
    (1, 1),
    (2, 2),
    (3, 3);

-- Thêm dữ liệu vào bảng Feedback_Type
INSERT INTO Feedback_Type (Feedback_Name)
VALUES 
    ('Bug Report'), 
    ('Feature Request'), 
    ('General Feedback');

-- Thêm dữ liệu vào bảng Feedback_For_System
INSERT INTO Feedback_For_System (UserID, Feedback_TypeID, Content)
VALUES 
    (1, 1, 'The application crashes when I try to upload a large document.'),
    (2, 2, 'Please add support for checking grammar in multiple languages.'),
    (3, 3, 'Overall, the tool is very helpful for my writing needs.');

-- Checked: 1 là đã kiểm tra, 0 là đang chờ kiểm tra

-- Thêm dữ liệu vào bảng Product
INSERT INTO Product (ProductName, Price, Description, Duration) VALUES
('Premium 1 Month', 27600.000, 'Everything included in Free, Adjust your writing tone, Rewrite full sentences, Write fluently in English, Catch accidental plagiarism, Generate text with 1,000 AI prompts, All app actions', 1),
('Premium 6 Months', 138000.000, 'Everything included in Free, Adjust your writing tone, Rewrite full sentences, Write fluently in English, Catch accidental plagiarism, Generate text with 1,000 AI prompts, All app actions', 6),
('Premium 1 Year', 230000.000, 'Everything included in Free, Adjust your writing tone, Rewrite full sentences, Write fluently in English, Catch accidental plagiarism, Generate text with 1,000 AI prompts, All app actions', 12),
('FREE', 0, 'Write without mistakes,
See your writing tone
Generate text with 100 AI prompts', 0),
('Premium Full Time', 460000.000, 'Everything included in Free, Adjust your writing tone, Rewrite full sentences, Write fluently in English, Catch accidental plagiarism, Generate text with 1,000 AI prompts, All app actions', 0);
-- Tạo bảng posttype
CREATE TABLE Post_Type (
    id INT PRIMARY KEY IDENTITY(1,1), -- Tự động tăng ID cho mỗi bản ghi mới
    ten_chu_de NVARCHAR(255) NOT NULL -- Tên chủ đề, không thể để trống
);
INSERT INTO Post_Type (ten_chu_de) 
VALUES ('Chủ đề 1'),
       ('Chủ đề 2'),
       ('Chủ đề 3');


-- Tạo bảng post
CREATE TABLE Post (
    id INT PRIMARY KEY IDENTITY(1,1), -- Tự động tăng ID cho mỗi bản ghi mới
	typeid INT NOT NULL, 
	title NVARCHAR(250),
    ngay_dang DATETIME NOT NULL,
    noi_dung NVARCHAR(MAX) NOT NULL,
    luot_thich INT DEFAULT 0,
    userid INT NOT NULL,
    FOREIGN KEY (userid) REFERENCES Users(ID),
	FOREIGN KEY (typeid) REFERENCES Post_Type(id)
);
CREATE TABLE List_Users_Like_Post (
    id INT PRIMARY KEY IDENTITY(1,1), -- Auto-increment ID for each new record
    postid INT NOT NULL, 
    userid INT NOT NULL,
    like_date DATETIME NOT NULL DEFAULT GETDATE(), -- Date when the report is created
   
    FOREIGN KEY (postid) REFERENCES Post(id),
    FOREIGN KEY (userid) REFERENCES Users(ID)
); 


CREATE TABLE Reports (
    id INT PRIMARY KEY IDENTITY(1,1), -- Auto-increment ID for each new record
    postid INT NOT NULL, 
    userid INT NOT NULL,
	
    report_date DATETIME NOT NULL DEFAULT GETDATE(), -- Date when the report is created
   
    FOREIGN KEY (postid) REFERENCES post(id),
    FOREIGN KEY (userid) REFERENCES Users(ID)
);


alter table Reports 
add 
content Nvarchar(Max);

-- Tạo bảng comment
CREATE TABLE Comment (
    id INT PRIMARY KEY IDENTITY(1,1), -- Tự động tăng ID cho mỗi bản ghi mới
    noi_dung NVARCHAR(MAX) NOT NULL, -- Nội dung của comment, không thể để trống
    ngay_dang DATETIME NOT NULL, -- Ngày đăng comment, không thể để trống
    post_id INT, -- Khóa ngoại liên kết với bảng post
    user_id INT, -- Khóa ngoại liên kết với bảng Users
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (user_id) REFERENCES Users(ID)
);

ALTER TABLE Comment
ADD cmt_id INT;


-- Thêm cột so_luong_comment vào bảng post
ALTER TABLE Post
ADD so_luong_comment INT DEFAULT 0;  

-- Tạo bảng favorite_posts
CREATE TABLE Favorite_Posts (
    id INT PRIMARY KEY IDENTITY(1,1), -- Tự động tăng ID cho mỗi bản ghi mới
    user_id INT NOT NULL, -- Khóa ngoại liên kết với bảng Users
    post_id INT NOT NULL, -- Khóa ngoại liên kết với bảng post
    ngay_yeu_thich DATE NOT NULL DEFAULT GETDATE(), -- Ngày thêm vào danh sách yêu thích, mặc định là ngày hiện tại
    FOREIGN KEY (user_id) REFERENCES Users(ID),
    FOREIGN KEY (post_id) REFERENCES post(id)
);

-- Thêm dữ liệu vào bảng ActivityType

-- Tạo bảng savepost
CREATE TABLE Save_Post (
    id INT PRIMARY KEY IDENTITY(1,1), -- Tự động tăng ID cho mỗi bản ghi mới
    user_id INT NOT NULL, -- Khóa ngoại liên kết với bảng Users
    post_id INT NOT NULL, -- Khóa ngoại liên kết với bảng post
    save_date DATETIME NOT NULL DEFAULT GETDATE(), -- Ngày lưu bài viết, mặc định là ngày hiện tại
    FOREIGN KEY (user_id) REFERENCES Users(ID),
    FOREIGN KEY (post_id) REFERENCES post(id)
);
-- Tạo bảng AccessLogs
CREATE TABLE Access_Logs (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT NOT NULL,
    LoginTime DATETIME NOT NULL DEFAULT GETDATE(),
    IPAddress NVARCHAR(50),
    UserAgent NVARCHAR(255),
    CONSTRAINT FK_AccessLogs_Users FOREIGN KEY (UserID) REFERENCES Users(ID)
);

ALTER TABLE Post
ADD number_report INT DEFAULT 0;

CREATE TABLE User_In_Forum (
    userid INT PRIMARY KEY,
    user_realname NVARCHAR(50),
    totalpost INT DEFAULT 0,
    totalcomment INT DEFAULT 0,
    totallike INT DEFAULT 0,
    totalreport INT DEFAULT 0,
    FOREIGN KEY (userid) REFERENCES Users(ID)
); 

ALTER TABLE User_In_Forum
ADD  status VARCHAR(20) DEFAULT 'Normal';	

go
CREATE TRIGGER Update_Total_Post
ON Post
AFTER INSERT
AS
BEGIN
    -- Update total post in userinforum table for users already in userinforum
    UPDATE ui
    SET totalpost = (
        SELECT COUNT(*)
        FROM post p
        WHERE p.userid = ui.userid
    )
    FROM User_In_Forum ui
    INNER JOIN (
        SELECT userid
        FROM inserted
    ) i ON ui.userid = i.userid
    
END;


go
CREATE TRIGGER Update_Total_Like
ON Post
AFTER UPDATE
AS
BEGIN
    -- Update total like in userinforum table
    UPDATE ui
    SET totallike = (
        SELECT SUM(p.luot_thich)
        FROM Post p
        WHERE p.userid = ui.userid
    )
    FROM User_In_Forum ui
    INNER JOIN inserted i ON ui.userid = i.userid;
END; 
go
CREATE TRIGGER Update_Total_Report
ON Post
AFTER INSERT, UPDATE
AS
BEGIN
    -- Update total report in userinforum table
    UPDATE ui
    SET totalreport = (
        SELECT SUM(p.number_report)
        FROM Post p
        WHERE p.userid = ui.userid
    )
    FROM User_In_Forum ui
    INNER JOIN (
        SELECT userid
        FROM inserted
    ) i ON ui.userid = i.userid;
END;
go

CREATE TRIGGER Update_Total_Comment
ON Comment
AFTER INSERT
AS
BEGIN
    -- Update total comment in userinforum table for users already in userinforum
    UPDATE ui
    SET totalcomment = (
        SELECT COUNT(*)
        FROM Comment c
        WHERE c.user_id = ui.userid
    )
    FROM User_In_Forum ui
    INNER JOIN (
        SELECT user_id
        FROM inserted
    ) i ON ui.userid = i.user_id
   
END;
go
    
CREATE TRIGGER Insert_User_In_Forum
ON Post
AFTER INSERT
AS
BEGIN
    -- Insert user into userinforum if not already present
    INSERT INTO User_In_Forum (userid, user_realname, totalpost)
    SELECT DISTINCT u.ID, u.Realname, 1
    FROM inserted i
    INNER JOIN Users u ON i.userid = u.ID
    WHERE NOT EXISTS (
        SELECT 1
        FROM User_In_Forum ui
        WHERE ui.userid = u.ID
    );
END;
go
CREATE TRIGGER Insert_User_In_Forum_On_Comment
ON Comment
AFTER INSERT
AS
BEGIN
    -- Insert user into userinforum if not already present
    INSERT INTO User_In_Forum (userid, user_realname, totalcomment)
    SELECT DISTINCT u.ID, u.Realname, 1
    FROM inserted i
    INNER JOIN Users u ON i.user_id = u.ID
    WHERE NOT EXISTS (
        SELECT 1
        FROM User_In_Forum ui
        WHERE ui.userid = u.ID
    );
END;
go

select * from Reports
