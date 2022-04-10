FROM python:3.9

ADD clean_up.py /

RUN pip install mysql-connector-python
RUN pip install boto3
RUN pip install botocore

CMD [ "python", "./clean_up.py" ]

