# Websecurity course project

## Intro

This project was designed to be as insecure as possible, so be warned if trying to run! 

Security problems from  [Owasp Top 10 Web Security List](https://www.owasp.org/index.php/Top_10_2013-Top_10). 

## Test accounts 

The appliction has the following test accounts: jules/jules, punkku-seppo/ps, soini/soini.

## Known security problems

### Cross Site Scripting (XSS)

1. Log in as any user
2. Make a payment to another user
3. Set a script as the 'message'
4. Log in as another user (in incognito, for instance)
5. Open received payment

Example script: ```<script>alert("This is not ok");</script>```

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



## Personal notes below

everything :D
insecure passwords!

Url handling -> anyone can view any payment by guessable id

Copying session cookie to a new browser -> pwnd

Payment message can contain scripts... 

making a payment with not enough funds gives money anyway, the joy! (add transactional, change order to make sense)