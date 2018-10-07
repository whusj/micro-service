FROM python-base:latest

ENV PYTHONPATH /

COPY message /message
COPY message_service.py /message_service.py

ENTRYPOINT [ "python", "message_service.py"]