FROM python:2.7

ADD clean_up.py /

RUN pip install mysql-connector-python
RUN pip install boto3

CMD [ "python", "./clean_up.py" ]

