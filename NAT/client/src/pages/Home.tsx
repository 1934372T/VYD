import Template from "pages/Template";
import { BaseContainer, BaseItem } from "components/views/ui";
import { HOME } from "models/constants";

const Home = () => {
  return (
    <Template name={HOME}>
      <BaseContainer>
        <BaseItem xs={12}>
          <></>
        </BaseItem>
      </BaseContainer>
    </Template>
  );
};

export default Home;
