# Websecurity course project

## Intro

This project was designed to be as insecure as possible, so be warned if trying to run! 

Security problems from  [Owasp Top 10 Web Security List](https://www.owasp.org/index.php/Top_10_2013-Top_10). 

## Test accounts 

The appliction has the following test accounts: jules/jules, punkku-seppo/ps, soini/soini.

## Known security problems

### Cross Site Scripting (XSS)

1. Log in as jules
2. Make a payment to soini
3. Set a script below as the 'message'
4. Log in as soini 
5. Open received payment
6. Every time the payment is opened, money is transfered to jules. 

Example script: ```<img src="http://localhost:8080/payments?euro=1&to=jules&message=moi+%3AD+" width="1" height="1" border="0">```

#### How to fix:
 
 In the template for `payment`, change utext to text, this makes it escaped and prevents stuff like this.


### Missing Function Level Access Control

1. Log in as punkku-seppo
2. Go to url `/payments/1`
3. A payment from soini to jules is visible.

##### How to fix:

Add an authenitification check in the controller for payments/{id}, making sure that the user attempting access is involved in payment.

### Broken Authentication and Session Management

1. Log in as jules
2. In chrome (ctrl+alt+c) (or other browser panel), under the Application tab, copy the value of JSESSIONID (double click on value, then right click and choose copy). 
3. open a new browser window (incognito)
4. Open developer options again (ctrl+alt+c)
5. In the browser console, type `document.cookie="JSESSIONID=valueofcookie"`
6. Press enter
7. Reload page
8. You should now be logged in as said user. 

##### How to fix:

Most important fix is to enable spring http headers in the security config, this also fixes a plethora of other security issues. 

### CSRF 

1. create a file called csrf.html, with code from below
2. While logged in as soini, open the created file in browser.
3. Money is transfered every time the page is opened

csrf.html: 

```
<html>
<body>
	<img src="http://localhost:8080/payments?euro=1&to=jules&message=moi+%3AD+" width="1" height="1" border="0">
</body>
</html>
```

##### How to fix: 

1. Disable making payments with GET-requests (POST-method exists, and works)
2. Enable CSRF-security from Spring config. (I disabled it)

### Unvalidated Redirects and Forwards

This is a combination of XSS and unvalidated redirecting!

1. Log-in as jules 
2. Send anyone message `<script> window.location = "http://www.google.com";</script>`
3. Log-in as user payment was sent to 
4. Open payment
5. User is forwarded to google (link could be anything)

#### How to fix:

There should be no redirects, fixing payment messages to 'escaped' (utext-> text), as above, and fixing http headers (as above)

### Security Misconfiguration

This one is related to almost everything above, but to fix this, to start off, enable all the disabled http headers and csrf-protection. 

