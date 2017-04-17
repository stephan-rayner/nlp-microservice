FROM node
EXPOSE 8080
ENV PORT 8080
ADD . /srv/staticapp
WORKDIR /srv/staticapp
RUN npm install unirest
RUN npm install
CMD ["npm", "start"]
