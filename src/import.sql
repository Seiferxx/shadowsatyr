INSERT INTO account ( id, nick_name, passwd, mail ) 
VALUES ( nextval( 'account_id' ), 'admin', '4fb9a58ff5842b98a6561c77cf50efb1d7d6ac5c57dd48b4737c18ad17b59e7e', 'mail@example.com' );

INSERT INTO blog_entry( id, message, account, date ) 
VALUES ( nextval( 'blog_entry_id' ), 'Hello, your CMS system has been deployed successfully', 1, NOW( ) ); 