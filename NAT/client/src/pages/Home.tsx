import Template from "pages/Template";
import { BaseContainer, BaseItem, Title } from "components/views/ui";
import { HOME } from "models/constants";

const Home = () => {
  return (
    <Template name={HOME}>
      <BaseContainer>
        <BaseItem xs={12}>
          <Title title={"論文管理システム by ボルシャック大和ドラゴン"} />
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default Home;
