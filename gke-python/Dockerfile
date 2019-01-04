FROM python:latest
RUN pip install --upgrade flask
RUN mkdir launch-service
COPY greeting.py  launch-service/
WORKDIR launch-service
EXPOSE 8899/tcp
ENTRYPOINT ["python3", "greeting.py"]
