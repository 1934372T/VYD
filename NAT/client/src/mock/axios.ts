import axios from "axios";
import MockAdapter from "axios-mock-adapter";

const mockAxios = new MockAdapter(axios);

mockAxios.onGet("/materials").reply(200, [
  {
    id: 1,
    title: "Aに関する研究",
    name: "神戸太郎",
    date: "2023-01-01",
  },
  {
    id: 2,
    title: "Aに関する研究",
    name: "神戸太郎",
    date: "2023-01-01",
  },
  {
    id: 3,
    title: "Aに関する研究",
    name: "神戸太郎",
    date: "2023-01-01",
  },
  {
    id: 4,
    title: "Aに関する研究",
    name: "神戸太郎",
    date: "2023-01-01",
  },
  {
    id: 5,
    title: "Aに関する研究",
    name: "神戸太郎",
    date: "2023-01-01",
  },
]);
