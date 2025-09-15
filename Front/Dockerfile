# -------- build (Angular) --------
FROM node:20-alpine AS build
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
# usa build:prod si existe, sino dejá "npm run build"
RUN npm run build:prod

# -------- runtime (Nginx) --------
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
# Angular 20 => dist/Front/browser
COPY --from=build /app/dist/Front/browser /usr/share/nginx/html
EXPOSE 80
