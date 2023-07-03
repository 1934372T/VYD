import Template from "pages/Template";
import {
  BaseContainer,
  BaseItem,
  GenericList,
  HyperLink,
  Title,
} from "components/views/ui";
import { HOME } from "models/constants";
import Timeline from "@mui/lab/Timeline";
import { timelineOppositeContentClasses } from "@mui/lab/TimelineOppositeContent";
import { TimeLineItem } from "components/utils/Timeline";
// icons
import LibraryBooksIcon from "@mui/icons-material/LibraryBooks";
import StorageIcon from "@mui/icons-material/Storage";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import { GenericListsType } from "models/types";

const tips: GenericListsType = [
  {
    text: "論文に`予備知識`という章は存在しない．",
  },
  {
    text: "先行研究ではなく，既存研究",
  },
];

const Home = () => {
  return (
    <Template name={HOME}>
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"新着情報"} />
          <Timeline
            sx={{
              [`& .${timelineOppositeContentClasses.root}`]: {
                flex: 0.1,
              },
            }}
          >
            <TimeLineItem
              icon={<LibraryBooksIcon />}
              date="2023/06/07"
              title="原稿追加"
              detail="2023年度の小研究中間報告会の原稿を追加しました．"
            />
            <TimeLineItem
              icon={<StorageIcon />}
              date="2023/05/17"
              title="ストレージ追加"
              detail={
                <>
                  <HyperLink
                    url="http://dazaifu:8888/s/Q54tQLW2wPKRZaD"
                    data={"オンラインストレージ"}
                  />
                  を作成しました．アクセスできない場合は
                  <HyperLink
                    url="http://10.32.131.128:8888/s/Q54tQLW2wPKRZaD"
                    data={"こちら"}
                  />
                  から．
                </>
              }
            />
            <TimeLineItem
              icon={<MenuBookIcon />}
              date="2023/05/09"
              title="書籍追加"
              detail="「資料 > 技術書一覧」ページに，電子書籍を追加しました．"
            />
          </Timeline>
        </BaseItem>
        <BaseItem xs={12}>
          <Title title={"研究生活 Tips"} />
          <GenericList data={tips} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default Home;
