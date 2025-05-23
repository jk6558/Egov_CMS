#베이스 이미지로 Node.js를 사용합니다.
FROM node:22-alpine
# 작업 디렉토리를 /app으로 설정합니다.
WORKDIR /app
COPY package.json /app
# 의존성을 설치합니다.
RUN npm install
#. = 도커파일이 있는 디렉토리
# /app = 여기에 위 파일들을 복사해서 실행
COPY . /app
# 애플리케이션이 실행될 포트를 노출합니다.
EXPOSE 3000
# 컨테이너가 실행될 때 실행할 명령어를 정의합니다.
CMD ["npm","start"]

