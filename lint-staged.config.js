export default {
  '*.(json|js|md|ts|yaml)': 'prettier --write',
  '*.(js|ts)': 'eslint --fix',
};
