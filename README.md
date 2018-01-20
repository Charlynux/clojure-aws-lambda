# AWS Basic Lambda with Clojure

As a start point, I was reading this article :

https://aws.amazon.com/fr/blogs/compute/clojure/

But I can't find a way to create the first sort of Lambda.

I choose to create a simple "Event Lambda" and track there every step.

## Installation awscli

### PIP

First you need to check, pip is installed (via `pip --version`). If not on MacOs, installation is pretty simple.

`sudo easy_install pip`

### awscli

`pip install --upgrade awscli --user`

### Add it to PATH

In your .bash_profile, add the following line.

`export PATH=$PATH:/Users/charles/Library/Python/2.7/bin`

## Configure AWS

### Key Id and Secret

In the console, click on your name in navbar. Choose 'security informations'.

Create an Access Key Id if needed.

### Roles

You need a role with specific rights to create a function.

In IAM, choose Role in left menu, then create a new role with strategy "AWSLambdaExecute".

### Region

Note your region name (ex. us-east-2)

### Configure

Run `aws configure` and give it the previous informations.

## Build your lambda

### Build uberjar

Your function code will be contain in an uberjar, to build it run `lein uberjar`

### Create the lambda

For the first deployment, you must read a 'create function' command.

```
aws lambda create-function \
    --function-name clj-hello  \
    --handler hello \
    --runtime java8  \
    --memory 512 \
    --timeout 10 \
    --role <YOUR_PREVIOUSLY_CREATED_ROLE> \
    --zip-file fileb://./target/clojure-aws-lambda-0.1.0-standalone.jar
```

### Update the lambda

After the first deployment, you just want to push new code to existing lambda.

```
aws lambda update-function-code \
    --function-name clj-hello \
    --zip-file fileb://./target/clojure-aws-lambda-0.1.0-standalone.jar
```
