docker build --force-rm --no-cache --pull -f web-ui/target/docker/Dockerfile -t acrchive-kd:1.0.0.0 .
docker run -it -d -p8080:8080 --name acrchive-kd1000 acrchive-kd:1.0.0.0 /bin/bash
docker exec -it acrchive-kd1000 bash
