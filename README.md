# maildump

## Usage

- First step, you have to generate the Dockerfile and install it in your Docker instance.

```sh
$ mvn package docker:build -DpushImage
```

- After that, you can simply run the image in a new container.

```sh
$ docker run -p 8080:8080 -p 25:25 -t dopse/maildump
```

- Now, you can go to the page [localhost:8080](http://localhost:8080) and see the emails when an email is sent to the port 25 of your local machine.