docker build --force-rm --no-cache --pull -f web-ui/target/docker/Dockerfile -t archive-kd:1.0.0.0 .
docker run -it -d -p8080:8080 --name archive-kd1000 archive-kd:1.0.0.0 /bin/bash
docker exec -it archive-kd1000 bash
