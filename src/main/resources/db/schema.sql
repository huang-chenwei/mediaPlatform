-- 創建資料庫
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'MediaPlatformDB')
BEGIN
    CREATE DATABASE MediaPlatformDB;
END
GO

USE MediaPlatformDB;
GO

-- 創建使用者表
    CREATE TABLE Users (
        UserID INT IDENTITY(1,1) PRIMARY KEY,
        UserName NVARCHAR(50) NOT NULL,
        Email NVARCHAR(100) NOT NULL UNIQUE,
        Phone NVARCHAR(20) NOT NULL UNIQUE,
        Password NVARCHAR(255) NOT NULL,
        Salt NVARCHAR(100) NOT NULL,
        CoverImage NVARCHAR(255),
        Biography NVARCHAR(MAX)
    );


-- 創建發文表

    CREATE TABLE Posts (
        PostID INT IDENTITY(1,1) PRIMARY KEY,
        UserID INT NOT NULL,
        Content NVARCHAR(MAX) NOT NULL,
        Image NVARCHAR(255),
        CreatedAt DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (UserID) REFERENCES Users(UserID)
    );


    CREATE TABLE Comments (
        CommentID INT IDENTITY(1,1) PRIMARY KEY,
        UserID INT NOT NULL,
        PostID INT NOT NULL,
        Content NVARCHAR(MAX) NOT NULL,
        CreatedAt DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (UserID) REFERENCES Users(UserID),
        FOREIGN KEY (PostID) REFERENCES Posts(PostID)
    );


-- 創建使用者註冊存儲過程

    DROP PROCEDURE sp_CreateUser


CREATE PROCEDURE sp_CreateUser
    @UserName NVARCHAR(50),
    @Email NVARCHAR(100),
    @Phone NVARCHAR(20),
    @Password NVARCHAR(255),
    @Salt NVARCHAR(100),
    @Biography NVARCHAR(MAX),
   @CoverImage NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRANSACTION;    
    BEGIN TRY
        INSERT INTO Users (UserName, Email, Phone, Password, Salt, Biography, CoverImage)
        VALUES (@UserName, @Email, @Phone, @Password, @Salt, @Biography, @CoverImage);
        
        COMMIT;
        SELECT SCOPE_IDENTITY() AS UserID;
    END TRY
    BEGIN CATCH
        ROLLBACK;
        THROW;
    END CATCH
    
